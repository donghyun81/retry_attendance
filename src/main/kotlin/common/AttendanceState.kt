package common

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

enum class AttendanceState(val result: String) {
    ATTENDANCE("출석"),
    PERCEPTION("지각"),
    ABSENCE("결석");

    companion object {
        fun convertAttendanceState(date: LocalDateTime): AttendanceState {
            if (date.dayOfWeek == DayOfWeek.MONDAY) return getMondayAttendanceState(date)
            return getRemainWeekDayAttendanceState(date)
        }

        private fun getMondayAttendanceState(date: LocalDateTime): AttendanceState {
            val mondayPerceptionTime = LocalTime.of(MONDAY_LIMIT_HOUR, PERCEPTION_MINUTE)
            val mondayAbsenceTime = LocalTime.of(MONDAY_LIMIT_HOUR, ABSENCE_MINUTE)
            if (date.toLocalTime() > mondayAbsenceTime) return ABSENCE
            if (date.toLocalTime() > mondayPerceptionTime) return PERCEPTION
            return ATTENDANCE
        }

        private fun getRemainWeekDayAttendanceState(date: LocalDateTime): AttendanceState {
            val remainPerceptionTime = LocalTime.of(REMAIN_WEEKDAY_LIMIT_HOUR, PERCEPTION_MINUTE)
            val remainAbsenceTime = LocalTime.of(REMAIN_WEEKDAY_LIMIT_HOUR, ABSENCE_MINUTE)
            if (date.dayOfWeek.isHoliday().not()) {
                if (date.toLocalTime() > remainAbsenceTime) return ABSENCE
                if (date.toLocalTime() > remainPerceptionTime) return PERCEPTION
            }
            return ATTENDANCE
        }

        private const val MONDAY_LIMIT_HOUR = 13
        private const val REMAIN_WEEKDAY_LIMIT_HOUR = 10
        private const val PERCEPTION_MINUTE = 5
        private const val ABSENCE_MINUTE = 5
    }
}