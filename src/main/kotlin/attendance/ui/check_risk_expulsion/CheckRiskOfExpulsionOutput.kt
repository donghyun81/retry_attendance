package attendance.ui.check_risk_expulsion

import attendance.domain.entity.CrewAttendance
import common.AttendanceState

class CheckRiskOfExpulsionOutput {
    fun printCrewsAtRisk(crewNames: List<String>, crewAttendances: List<CrewAttendance>) {
        println("제적 위험자 조회 결과")
        val sortedCrewNames =
            crewNames.sortedWith(compareByDescending<String> { name -> crewAttendances.count { name == it.nickname && it.attendanceState == AttendanceState.ABSENCE } }.thenByDescending { name -> crewAttendances.count { name == it.nickname && it.attendanceState == AttendanceState.PERCEPTION } })
        for (crewName in sortedCrewNames) {
            val perceptionCount =
                crewAttendances.count { crewName == it.nickname && it.attendanceState == AttendanceState.PERCEPTION && it.datetime.dayOfMonth < 13 }
            val absenceCount =
                crewAttendances.count { crewName == it.nickname && it.attendanceState == AttendanceState.ABSENCE && it.datetime.dayOfMonth < 13 }
            val crewState = getCrewState(perceptionCount, absenceCount)
            if (crewState == "") continue
            println("- ${crewName}: 결석 ${absenceCount}회, 지각 ${perceptionCount}회 (${crewState})\"")
        }
    }

    private fun getCrewState(perceptionCount: Int, absenceCount: Int): String {
        if (perceptionCount.div(3) + absenceCount >= 5) return "제적"
        if (perceptionCount.div(3) + absenceCount >= 3) return "면담"
        if (perceptionCount.div(3) + absenceCount >= 2) return "경고"
        return ""
    }
}