package attendance.domain

import java.io.FileReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AttendanceService(
    private val crewAttendanceValidator: CrewAttendanceValidator = CrewAttendanceValidator()
) {
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

    private fun getCrewNames() = crewAttendances.map { it.nickname }

    fun validateNickName(inputName: String) {
        crewAttendanceValidator.validateNickName(getCrewNames(), inputName)
    }

    fun validateHasAttendance(name: String, day: Int) {
        val hasAttendance = crewAttendances.any { it.nickname == name && it.datetime.dayOfMonth == day }
        crewAttendanceValidator.validateAttendance(hasAttendance)
    }

    fun addAttendance(name: String, dateTime: LocalDateTime) {
        crewAttendances.add(CrewAttendance(name, dateTime))
    }
}