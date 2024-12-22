package attendance.controller.check_risk_expulsion

import attendance.domain.service.AttendanceService
import attendance.view.check_risk_expulsion.CheckRiskOfExpulsionOutput

class CheckRiskOfExpulsionController(private val attendanceService: AttendanceService) {

    private val outputView = CheckRiskOfExpulsionOutput()

    fun run() {
        val crewNames = attendanceService.getCrewNames()
        val crewAttendances = attendanceService.getCrewAttendances()
        outputView.printCrewsAtRisk(crewNames, crewAttendances)
    }
}