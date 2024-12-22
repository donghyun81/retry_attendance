package attendance.view

import attendance.domain.CrewAttendance
import common.AttendanceState

class CheckRiskOfExpulsionOutput {
    fun printCrewsAtRisk(crewNames: List<String>, crewAttendances: List<CrewAttendance>) {
        println("제적 위험자 조회 결과")
        val sortedCrewNames =
            crewNames.sortedBy { name -> crewAttendances.count { name == it.nickname && it.attendanceState == AttendanceState.ABSENCE } }
                .sortedWith(
                    compareBy({ name -> crewAttendances.count { name == it.nickname && it.attendanceState == AttendanceState.ABSENCE } },
                        { name -> crewAttendances.count { name == it.nickname && it.attendanceState == AttendanceState.PERCEPTION } })
                )
        for (crewName in sortedCrewNames) {
            val perceptionCount = crewAttendances.count { it.attendanceState == AttendanceState.PERCEPTION }
            val absenceCount = crewAttendances.count { it.attendanceState == AttendanceState.ABSENCE }
            val crewState = getCrewState(perceptionCount, absenceCount)
            println("- ${crewName}: 결석 ${absenceCount}}회, 지각 ${perceptionCount}회 (${crewState})\"")
        }
    }

    private fun getCrewState(perceptionCount: Int, absenceCount: Int): String {
        if (perceptionCount.div(3) + absenceCount >= 2) return "경고"
        if (perceptionCount.div(3) + absenceCount >= 3) return "면담"
        if (perceptionCount.div(3) + absenceCount >= 5) return "제적"
        return ""
    }
}