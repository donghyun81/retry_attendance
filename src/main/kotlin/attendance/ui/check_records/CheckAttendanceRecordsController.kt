package attendance.ui.check_records

import attendance.domain.service.AttendanceService
import camp.nextstep.edu.missionutils.DateTimes

class CheckAttendanceRecordsController(
    private val attendanceService: AttendanceService,
) {
    private val inputView = CheckAttendanceRecordsInputView()
    private val outputView = CheckAttendanceRecordsOutputView()
    private val today = DateTimes.now()

    fun run() {
        val userName = inputView.readQueryName()
        attendanceService.validateNickName(userName)
        val crewAttendances =
            attendanceService.getCrewAttendances(userName).filter { it.datetime.dayOfMonth < today.dayOfMonth }
        outputView.printCrewAttendances(crewAttendances)
    }
}