package attendance.view.check_records

import attendance.domain.entity.CrewAttendance
import common.AttendanceState
import common.formatTwoDigit
import common.getDayOfWeek

class CheckAttendanceRecordsOutputView {

    fun printCrewAttendances(attendances: List<CrewAttendance>) {
        println("이번 달 ${attendances.first().nickname}의 출석 기록입니다.\n")
        attendances.forEach { attendance ->
            if (attendance.datetime.hour == 23 && attendance.datetime.minute ==59) println("${attendance.datetime.monthValue}월 ${attendance.datetime.dayOfMonth}일 ${attendance.datetime.dayOfWeek.getDayOfWeek()} --:-- (${attendance.attendanceState.result})")
            else println("${attendance.datetime.monthValue}월 ${attendance.datetime.dayOfMonth.formatTwoDigit()}일 ${attendance.datetime.dayOfWeek.getDayOfWeek()} ${attendance.datetime.toLocalTime()} (${attendance.attendanceState.result})")
        }
        printState(attendances)
    }

    private fun printState(attendances: List<CrewAttendance>) {
        val attendanceCount = attendances.count { it.attendanceState == AttendanceState.ATTENDANCE }
        val perceptionCount = attendances.count { it.attendanceState == AttendanceState.PERCEPTION }
        val absenceCount = attendances.count { it.attendanceState == AttendanceState.ABSENCE }
        printAttendancesState(attendanceCount, perceptionCount, absenceCount)
        printCrewState(perceptionCount, absenceCount)
    }

    private fun printAttendancesState(attendanceCount: Int, perceptionCount: Int, absenceCount: Int) {
        println("출석: ${attendanceCount}회")
        println("지각: ${perceptionCount}회")
        println("결석: ${absenceCount}회\n")
    }

    private fun printCrewState(perceptionCount: Int, absenceCount: Int) {
        when {
            perceptionCount.div(3) + absenceCount >= 2 -> println("경고 대상자입니다.")
            perceptionCount.div(3) + absenceCount >= 3 -> println("면담 대상자입니다.")
            perceptionCount.div(3) + absenceCount >= 5 -> println("제적 대상자입니다.")
        }
    }
}