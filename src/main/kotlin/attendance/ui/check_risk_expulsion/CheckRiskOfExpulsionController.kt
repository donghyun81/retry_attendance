package attendance.ui.check_risk_expulsion

import attendance.domain.service.AttendanceService

class CheckRiskOfExpulsionController(private val attendanceService: AttendanceService) {

    private val outputView = CheckRiskOfExpulsionOutput()

    fun run() {
        val crewNames = attendanceService.getCrewNames()
        val crewAttendances = attendanceService.getCrewAttendances()
        outputView.printCrewsAtRisk(crewNames, crewAttendances)
    }
}