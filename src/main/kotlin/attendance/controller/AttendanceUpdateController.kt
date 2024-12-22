package attendance.controller

import attendance.domain.AttendanceService
import attendance.view.AttendanceUpdateInputView
import attendance.view.AttendanceUpdateOutputView

class AttendanceUpdateController(
    private val attendanceService: AttendanceService
) {
    private val inputView = AttendanceUpdateInputView()
    private val outputView = AttendanceUpdateOutputView()
    fun run() {
        val name = inputView.readUpdateName()
        val day = inputView.readUpdateDate()
        val time = inputView.readUpdateTime()
    }
}