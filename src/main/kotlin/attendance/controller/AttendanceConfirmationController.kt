package attendance.controller

import attendance.domain.AttendanceService
import attendance.domain.DateValidator
import attendance.view.AttendanceConfirmationInputView
import attendance.view.AttendanceConfirmationOutputView
import camp.nextstep.edu.missionutils.DateTimes
import common.getDayOfWeek
import common.isHoliday
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
        attendanceService.validateHasAttendance(nickName, today.dayOfMonth)
        val attendanceDateTime = LocalDateTime.of(today.toLocalDate(), time)
        attendanceService.addAttendance(nickName, attendanceDateTime)
        outputView.printConfirmationResult(attendanceDateTime)
    }

    private fun getArrivalTime(time: String): LocalTime {
        require(time.split(":").size == 2)
        val (hourInput, minuteInput) = time.split(":")
        return LocalTime.of(dateValidator.validateHour(hourInput), dateValidator.validateMinute(minuteInput))
    }
}