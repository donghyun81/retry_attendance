package attendance.domain

import common.AttendanceState
import java.time.LocalDateTime

data class CrewAttendance(val nickname: String, val datetime: LocalDateTime,val attendanceState: AttendanceState)