package attendance.domain.service

import attendance.domain.entity.CrewAttendance
import attendance.domain.validator.CrewAttendanceValidator
import camp.nextstep.edu.missionutils.DateTimes
import common.AttendanceState
import common.isHoliday
import java.io.FileReader
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
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
            val (nickName, dateTimeInput) = it.split(",")
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val dateTime = LocalDateTime.parse(dateTimeInput, formatter)
            val attendanceState = AttendanceState.convertAttendanceState(dateTime)
            CrewAttendance(nickName, dateTime, attendanceState)
        }.toMutableList()
        val today = DateTimes.now()
        for (day in 1..<today.dayOfMonth) addAbsence(getCrewNames(), today, day)
    }

    private fun addAbsence(crews: List<String>, today: LocalDateTime, day: Int) {
        crews.forEach { name ->
            val crewAttendance = crewAttendances.find { it.nickname == name && it.datetime.dayOfMonth == day }
            val currentDate = LocalDate.of(today.year, today.monthValue, day)
            if (crewAttendance == null && currentDate.dayOfWeek.isHoliday().not() && day != 25) {
                val noTime = LocalTime.of(23, 59)
                addAttendance(name, LocalDateTime.of(currentDate, noTime))
            }
        }
    }

    fun getCrewAttendances() = crewAttendances.map { it.copy() }

    fun getCrewNames() = crewAttendances.map { it.nickname }.distinct()

    fun validateNickName(inputName: String) {
        crewAttendanceValidator.validateNickName(getCrewNames(), inputName)
    }

    fun validateUpdate(name: String, day: Int) {
        val hasAttendance = crewAttendances.any { it.nickname == name && it.datetime.dayOfMonth == day }
        crewAttendanceValidator.validateUpdateAttendance(hasAttendance)
    }

    fun validateAttendance(name: String, day: Int) {
        val hasAttendance = crewAttendances.any { it.nickname == name && it.datetime.dayOfMonth == day }
        crewAttendanceValidator.validateAttendance(hasAttendance)
    }

    fun addAttendance(name: String, dateTime: LocalDateTime) {
        val attendanceState = AttendanceState.convertAttendanceState(dateTime)
        crewAttendances.add(CrewAttendance(name, dateTime, attendanceState))
    }

    fun updateAttendance(name: String, day: Int, updateTime: LocalTime): CrewAttendance {
        val attendanceIndex = crewAttendances.indexOfFirst { it.nickname == name && day == it.datetime.dayOfMonth }
        require(attendanceIndex != -1) { "[ERROR] 아직 수정할 수 없습니다." }
        val currentAttendance = crewAttendances[attendanceIndex]
        val updateDateTime = currentAttendance.datetime.let { LocalDateTime.of(it.toLocalDate(), updateTime) }
        val attendanceState = AttendanceState.convertAttendanceState(updateDateTime)
        crewAttendances[attendanceIndex] = CrewAttendance(name, updateDateTime, attendanceState)
        return currentAttendance
    }

    fun getCrewAttendances(name: String) =
        crewAttendances.filter { it.nickname == name }.sortedBy { it.datetime.dayOfMonth }
}