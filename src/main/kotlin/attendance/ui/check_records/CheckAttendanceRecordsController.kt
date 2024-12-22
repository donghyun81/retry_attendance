package attendance.ui.check_records

import attendance.domain.service.AttendanceService

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