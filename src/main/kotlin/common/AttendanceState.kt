package common

import java.time.DayOfWeek
import java.time.LocalDateTime

enum class AttendanceState(val result: String) {
    ATTENDANCE("출석"),
    PERCEPTION("지각"),
    ABSENCE("결석");

    companion object {
        fun convertAttendanceState(date: LocalDateTime): AttendanceState {
            if (date.dayOfWeek == DayOfWeek.MONDAY && date.hour >= 13 && date.minute > 30) return ABSENCE
            if (date.dayOfWeek == DayOfWeek.MONDAY && date.hour >= 13 && date.minute > 5) return PERCEPTION
            if (date.dayOfWeek.isHoliday().not() && date.hour >= 10 && date.minute > 30) return ABSENCE
            if (date.dayOfWeek.isHoliday().not() && date.hour >= 10 && date.minute > 5) return PERCEPTION
            return ATTENDANCE
        }
    }
}