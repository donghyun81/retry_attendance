package attendance.domain

import java.io.FileReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AttendanceService {
    private lateinit var crewAttendances: MutableList<CrewAttendance>

    init {
        initCrewAttendance()
    }

    private fun initCrewAttendance() {
        crewAttendances = FileReader("src/main/resources/attendances.csv").readLines().drop(1).map {
            val (nickName, dateTime) = it.split(",")
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            CrewAttendance(nickName, LocalDateTime.parse(dateTime, formatter))
        }.toMutableList()
    }

    fun getCrewNames() = crewAttendances.map { it.nickname }

    fun hasAttendance(name: String, day: Int): Boolean {
        return crewAttendances.any { it.nickname == name && it.datetime.dayOfMonth == day }
    }
}