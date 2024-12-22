package attendance.view

import common.AttendanceState
import common.getDayOfWeek
import java.time.LocalDateTime

class AttendanceConfirmationOutputView {
    fun printConfirmationResult(dateTime: LocalDateTime) {
        val attendanceState = AttendanceState.convertAttendanceState(dateTime)
        println(
            "${dateTime.monthValue}월 ${dateTime.dayOfMonth}일 ${dateTime.dayOfWeek.getDayOfWeek()} ${
                dateTime.toLocalTime()
            } (${attendanceState.result})"
        )
    }
}