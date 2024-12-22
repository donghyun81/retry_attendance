package attendance.ui.update

import common.AttendanceState
import common.getDayOfWeek
import java.time.LocalDateTime

class AttendanceUpdateOutputView {

    fun printUpdateResult(previousDateTime: LocalDateTime, updateTime: LocalDateTime) {
        val previousAttendanceState = AttendanceState.convertAttendanceState(previousDateTime)
        val updateAttendanceState = AttendanceState.convertAttendanceState(updateTime)
        println("${previousDateTime.monthValue}월 ${previousDateTime.dayOfMonth}일 ${previousDateTime.dayOfWeek.getDayOfWeek()} ${previousDateTime.toLocalTime()} (${previousAttendanceState.result}) -> ${updateTime.toLocalTime()} (${updateAttendanceState.result}) 수정 완료!")
    }
}