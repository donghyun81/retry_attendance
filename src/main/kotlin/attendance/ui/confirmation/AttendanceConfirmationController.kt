package attendance.ui.confirmation

import attendance.domain.service.AttendanceService
import attendance.domain.validator.DateValidator
import camp.nextstep.edu.missionutils.DateTimes
import common.TIME_DELIMITER
import java.time.LocalDateTime
import java.time.LocalTime

class AttendanceConfirmationController(
    private val attendanceService: AttendanceService,
    private val dateValidator: DateValidator
) {
    private val inputView = AttendanceConfirmationInputView()
    private val outputView = AttendanceConfirmationOutputView()
    private val today = DateTimes.now()

    fun run() {
        dateValidator.validateSchoolDay(today)
        val nickName = inputView.readAttendanceName()
        attendanceService.validateNickName(nickName)
        val time = getArrivalTime(inputView.readArrivalTime())
        attendanceService.validateAttendance(nickName, today.dayOfMonth)
        val attendanceDateTime = LocalDateTime.of(today.toLocalDate(), time)
        attendanceService.addAttendance(nickName, attendanceDateTime)
        outputView.printConfirmationResult(attendanceDateTime)
    }

    private fun getArrivalTime(time: String): LocalTime {
        require(time.split(TIME_DELIMITER).size == 2)
        val (hourInput, minuteInput) = time.split(TIME_DELIMITER)
        return LocalTime.of(dateValidator.validateHour(hourInput), dateValidator.validateMinute(minuteInput))
    }
}