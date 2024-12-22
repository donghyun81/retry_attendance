package attendance.controller

import attendance.domain.AttendanceService
import attendance.view.CheckAttendanceRecordsInputView
import attendance.view.CheckAttendanceRecordsOutputView

class CheckAttendanceRecordsController(
    private val attendanceService: AttendanceService,
) {
    private val inputView = CheckAttendanceRecordsInputView()
    private val outputView = CheckAttendanceRecordsOutputView()

    fun run() {
        val userName = inputView.readQueryName()
        attendanceService.validateNickName(userName)
    }
}