package attendance.controller.check_records

import attendance.domain.service.AttendanceService
import attendance.view.check_records.CheckAttendanceRecordsInputView
import attendance.view.check_records.CheckAttendanceRecordsOutputView

class CheckAttendanceRecordsController(
    private val attendanceService: AttendanceService,
) {
    private val inputView = CheckAttendanceRecordsInputView()
    private val outputView = CheckAttendanceRecordsOutputView()

    fun run() {
        val userName = inputView.readQueryName()
        attendanceService.validateNickName(userName)
        val crewAttendances = attendanceService.getCrewAttendances(userName)
        outputView.printCrewAttendances(crewAttendances)
    }
}