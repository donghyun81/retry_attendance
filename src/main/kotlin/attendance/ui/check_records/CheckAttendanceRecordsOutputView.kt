package attendance.ui.check_records

import attendance.domain.entity.CrewAttendance
import common.AttendanceState
import common.CrewAttendanceState
import common.formatTwoDigit
import common.getDayOfWeek

class CheckAttendanceRecordsOutputView {

    fun printCrewAttendances(attendances: List<CrewAttendance>) {
        println("이번 달 ${attendances.first().nickname}의 출석 기록입니다.\n")
        attendances.forEach { attendance ->
            if (attendance.datetime.hour == 23 && attendance.datetime.minute == 59) println("${attendance.datetime.monthValue}월 ${attendance.datetime.dayOfMonth}일 ${attendance.datetime.dayOfWeek.getDayOfWeek()} --:-- (${attendance.attendanceState.result})")
            else println("${attendance.datetime.monthValue}월 ${attendance.datetime.dayOfMonth.formatTwoDigit()}일 ${attendance.datetime.dayOfWeek.getDayOfWeek()} ${attendance.datetime.toLocalTime()} (${attendance.attendanceState.result})")
        }
        printState(attendances)
    }

    private fun printState(attendances: List<CrewAttendance>) {
        val attendanceCount = attendances.count { it.attendanceState == AttendanceState.ATTENDANCE }
        val perceptionCount = attendances.count { it.attendanceState == AttendanceState.PERCEPTION }
        val absenceCount = attendances.count { it.attendanceState == AttendanceState.ABSENCE }
        printAttendancesState(attendanceCount, perceptionCount, absenceCount)
        val crewAttendanceState = CrewAttendanceState.getCrewAttendanceState(perceptionCount, absenceCount)
        printCrewState(crewAttendanceState)
    }

    private fun printAttendancesState(attendanceCount: Int, perceptionCount: Int, absenceCount: Int) {
        println("출석: ${attendanceCount}회")
        println("지각: ${perceptionCount}회")
        println("결석: ${absenceCount}회\n")
    }

    private fun printCrewState(crewAttendanceState: CrewAttendanceState) {
        when (crewAttendanceState) {
            CrewAttendanceState.EXPULSION -> println("${crewAttendanceState.state} 대상자입니다.")
            CrewAttendanceState.INTERVIEW -> println("${crewAttendanceState.state} 대상자입니다.")
            CrewAttendanceState.WARNING -> println("${crewAttendanceState.state} 대상자입니다.")
            CrewAttendanceState.NON -> Unit
        }
    }
}