package attendance.controller

import attendance.domain.AttendanceService
import attendance.domain.DateValidator
import attendance.view.AttendanceUpdateInputView
import attendance.view.AttendanceUpdateOutputView
import camp.nextstep.edu.missionutils.DateTimes
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
        val day = inputView.readUpdateDate()
        val time = getUpdateTime(inputView.readUpdateTime())
    }

    private fun getUpdateTime(time: String): LocalTime {
        require(time.split(":").size == 2)
        val (hourInput, minuteInput) = time.split(":")
        return LocalTime.of(dateValidator.validateHour(hourInput), dateValidator.validateMinute(minuteInput))
    }
}