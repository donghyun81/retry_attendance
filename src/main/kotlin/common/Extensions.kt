@file:Suppress("UNREACHABLE_CODE")

package common

import java.time.DayOfWeek

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