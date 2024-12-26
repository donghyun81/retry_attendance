@file:Suppress("UNREACHABLE_CODE")

package common

import java.text.DecimalFormat
import java.time.DayOfWeek
import java.time.LocalDateTime

fun DayOfWeek.getDayOfWeek(): String {
    return when (this) {
        DayOfWeek.MONDAY -> return "월요일"
        DayOfWeek.TUESDAY -> return "화요일"
        DayOfWeek.WEDNESDAY -> return "수요일"
        DayOfWeek.THURSDAY -> return "목요일"
        DayOfWeek.FRIDAY -> return "금요일"
        DayOfWeek.SATURDAY -> return "토요일"
        DayOfWeek.SUNDAY -> return "일요일"
    }
}

fun DayOfWeek.isHoliday(): Boolean {
    return when (this) {
        DayOfWeek.MONDAY -> return false
        DayOfWeek.TUESDAY -> return false
        DayOfWeek.WEDNESDAY -> return false
        DayOfWeek.THURSDAY -> return false
        DayOfWeek.FRIDAY -> return false
        DayOfWeek.SATURDAY -> return true
        DayOfWeek.SUNDAY -> return true
    }
}

fun LocalDateTime.toTime(): String {
    if (this.hour == ABSENCE_HOUR && this.minute == ABSENCE_MINUTE) return "--:--"
    return this.toLocalTime().toString()
}

fun Int.formatTwoDigit(): String {
    val formatter = DecimalFormat("00")
    return formatter.format(this)
}