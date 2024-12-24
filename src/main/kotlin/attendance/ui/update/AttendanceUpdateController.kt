package attendance.ui.update

import attendance.domain.service.AttendanceService
import attendance.domain.validator.DateValidator
import camp.nextstep.edu.missionutils.DateTimes
import java.time.LocalDateTime
import java.time.LocalTime

class AttendanceUpdateController(
    private val attendanceService: AttendanceService,
    private val dateValidator: DateValidator
) {
    private val inputView = AttendanceUpdateInputView()
    private val outputView = AttendanceUpdateOutputView()
    private val today = DateTimes.now()

    fun run() {
        dateValidator.validateSchoolDay(today = today)
        val name = inputView.readUpdateName()
        attendanceService.validateNickName(name)
        val day = dateValidator.validateUpdateDay(inputView.readUpdateDate())
        attendanceService.validateUpdate(name, day)
        val time = getUpdateTime(inputView.readUpdateTime())
        val previousAttendance = attendanceService.updateAttendance(name, day, time).datetime
        val updateDateTime = LocalDateTime.of(previousAttendance.toLocalDate(), time)
        outputView.printUpdateResult(previousAttendance, updateDateTime)
    }

    private fun getUpdateTime(time: String): LocalTime {
        require(time.split(":").size == 2)
        val (hourInput, minuteInput) = time.split(":")
        return LocalTime.of(dateValidator.validateHour(hourInput), dateValidator.validateMinute(minuteInput))
    }
}