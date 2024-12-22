package attendance.domain

import common.getDayOfWeek
import common.isHoliday
import java.time.LocalDateTime

class DateValidator {

    fun validateDay(dayInput: String): Int {
        val day = requireNotNull(dayInput.toIntOrNull()) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(day in 1..31) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        return day
    }

    fun validateSchoolDay(today: LocalDateTime) {
        val isHoliday = today.dayOfWeek.isHoliday()
        val isChristmas = today.dayOfMonth == 25
        require((isHoliday || isChristmas).not()) { "[ERROR] ${today.monthValue}월 ${today.dayOfMonth}일 ${today.dayOfWeek.getDayOfWeek()}은 등교일이 아닙니다." }
    }

    fun validateHour(hourInput: String): Int {
        val hour = requireNotNull(hourInput.toIntOrNull()) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(hour in 8..22) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        return hour
    }

    fun validateMinute(minuteInput: String): Int {
        val minute = requireNotNull(minuteInput.toIntOrNull()) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(minute in 0..59) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        return minute
    }
}