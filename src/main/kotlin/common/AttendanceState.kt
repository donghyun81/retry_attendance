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
            val mondayPerceptionTime = LocalTime.of(13, 5)
            val mondayAbsenceTime = LocalTime.of(13, 30)
            if (date.toLocalTime() > mondayAbsenceTime) return ABSENCE
            if (date.toLocalTime() > mondayPerceptionTime) return PERCEPTION
            return ATTENDANCE
        }

        private fun getRemainWeekDayAttendanceState(date: LocalDateTime): AttendanceState {
            val remainPerceptionTime = LocalTime.of(10, 5)
            val remainAbsenceTime = LocalTime.of(10, 30)
            if (date.dayOfWeek.isHoliday().not()) {
                if (date.toLocalTime() > remainAbsenceTime) return ABSENCE
                if (date.toLocalTime() > remainPerceptionTime) return PERCEPTION
            }
            return ATTENDANCE
        }
    }
}