package attendance.controller

import attendance.domain.AttendanceService
import attendance.view.CheckRiskOfExpulsionOutput

class CheckRiskOfExpulsionController(private val attendanceService: AttendanceService) {

    private val outputView = CheckRiskOfExpulsionOutput()

    fun run() {
        val crewNames = attendanceService.getCrewNames()
        val crewAttendances = attendanceService.getCrewAttendances()
        outputView.printCrewsAtRisk(crewNames, crewAttendances)
    }
}