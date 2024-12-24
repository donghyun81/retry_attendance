package attendance.ui.check_risk_expulsion

import attendance.domain.entity.CrewAttendance
import attendance.domain.service.AttendanceService
import common.AttendanceState

class CheckRiskOfExpulsionController(private val attendanceService: AttendanceService) {

    private val outputView = CheckRiskOfExpulsionOutput()

    fun run() {
        val crewNames = attendanceService.getCrewNames()
        val crewAttendances = attendanceService.getCrewAttendances()
        val sortedCrewNames = getSortedAtRiskCrewNames(crewNames, crewAttendances)
        outputView.printCrewsAtRisk(sortedCrewNames, crewAttendances)
    }

    private fun getSortedAtRiskCrewNames(crewNames: List<String>, crewAttendances: List<CrewAttendance>): List<String> {
        return crewNames.sortedWith(compareByDescending<String> { name -> crewAttendances.count { name == it.nickname && it.attendanceState == AttendanceState.ABSENCE } }.thenByDescending { name -> crewAttendances.count { name == it.nickname && it.attendanceState == AttendanceState.PERCEPTION } })
    }
}