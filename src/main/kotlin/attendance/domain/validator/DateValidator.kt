package attendance.domain.validator

import common.*
import java.time.LocalDateTime

class DateValidator {

    fun validateUpdateDay(today: LocalDateTime, dayInput: String): Int {
        val day = requireNotNull(dayInput.toIntOrNull()) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(day in START_DAY..END_DAY) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(today.dayOfMonth >= day) { "[ERROR] 아직 수정할 수 없습니다." }
        return day
    }

    fun validateSchoolDay(today: LocalDateTime) {
        val isHoliday = today.dayOfWeek.isHoliday()
        val isChristmas = today.dayOfMonth == CHRISTMAS_DAY
        require((isHoliday || isChristmas).not()) { "[ERROR] ${today.monthValue}월 ${today.dayOfMonth}일 ${today.dayOfWeek.getDayOfWeek()}은 등교일이 아닙니다." }
    }

    fun validateHour(hourInput: String): Int {
        val hour = requireNotNull(hourInput.toIntOrNull()) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(hour in LocalDateTime.MIN.hour..LocalDateTime.MAX.hour) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(hour in START_OPERATING_HOURS..END_OPERATING_HOURS) { "[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다." }
        return hour
    }

    fun validateMinute(minuteInput: String): Int {
        val minute = requireNotNull(minuteInput.toIntOrNull()) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(minute in LocalDateTime.MIN.minute..LocalDateTime.MAX.minute) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        return minute
    }

    companion object {
        private const val START_OPERATING_HOURS = 8
        private const val END_OPERATING_HOURS = 22
    }
}