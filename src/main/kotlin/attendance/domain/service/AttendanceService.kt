package attendance.domain.service

import attendance.domain.entity.CrewAttendance
import attendance.domain.validator.CrewAttendanceValidator
import camp.nextstep.edu.missionutils.DateTimes
import common.*
import java.io.FileReader
import java.io.FileWriter
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
        crewAttendances = FileReader(ATTENDANCE_PATH).readLines().drop(FILE_ATTRIBUTE_LINE).map {
            val (nickName, dateTimeInput) = it.split(BASIC_DELIMITER)
            val formatter = DateTimeFormatter.ofPattern(ATTENDANCE_DATE_FORMAT)
            val dateTime = LocalDateTime.parse(dateTimeInput, formatter)
            val attendanceState = AttendanceState.convertAttendanceState(dateTime)
            CrewAttendance(nickName, dateTime, attendanceState)
        }.toMutableList()
        val today = DateTimes.now()
        for (day in START_DAY..<today.dayOfMonth) addAbsence(getCrewNames(), today, day)
    }

    private fun addAbsence(crews: List<String>, today: LocalDateTime, day: Int) {
        crews.forEach { name ->
            val crewAttendance = crewAttendances.find { it.nickname == name && it.datetime.dayOfMonth == day }
            val currentDate = LocalDate.of(today.year, today.monthValue, day)
            if (crewAttendance == null && currentDate.dayOfWeek.isHoliday().not() && day != CHRISTMAS_DAY) {
                val noTime = LocalTime.of(ABSENCE_HOUR, ABSENCE_MINUTE)
                addAttendance(name, LocalDateTime.of(currentDate, noTime), isNoTime = true)
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

    fun addAttendance(name: String, dateTime: LocalDateTime, isNoTime: Boolean = false) {
        val attendanceState = AttendanceState.convertAttendanceState(dateTime)
        val formatter = DateTimeFormatter.ofPattern(ATTENDANCE_DATE_FORMAT)
        if (isNoTime.not()) {
            val fileWriter = FileWriter(ATTENDANCE_PATH, true).append("\n${name},${dateTime.format(formatter)}")
            fileWriter.flush()
        }
        crewAttendances.add(CrewAttendance(name, dateTime, attendanceState))
    }

    fun updateAttendance(name: String, day: Int, updateTime: LocalTime): CrewAttendance {
        val attendanceIndex = crewAttendances.indexOfFirst { it.nickname == name && day == it.datetime.dayOfMonth }
        require(attendanceIndex != -1) { "[ERROR] 아직 수정할 수 없습니다." }
        val currentAttendance = crewAttendances[attendanceIndex]
        val updateDateTime = currentAttendance.datetime.let { LocalDateTime.of(it.toLocalDate(), updateTime) }
        val attendanceState = AttendanceState.convertAttendanceState(updateDateTime)
        crewAttendances[attendanceIndex] = CrewAttendance(name, updateDateTime, attendanceState)
        updateFileAttendance()
        return currentAttendance
    }

    private fun updateFileAttendance() {
        val initFileWriter = FileWriter(ATTENDANCE_PATH).append("nickname,datetime")
        initFileWriter.flush()
        crewAttendances.filter { it.datetime.hour != 23 && it.datetime.minute != 59 }.forEach { attendance ->
            val formatter = DateTimeFormatter.ofPattern(ATTENDANCE_DATE_FORMAT)
            val fileWriter = FileWriter(ATTENDANCE_PATH, true)
            fileWriter.append("\n${attendance.nickname},${attendance.datetime.format(formatter)}")
            fileWriter.flush()
        }
    }

    fun getCrewAttendances(name: String) =
        crewAttendances.filter { it.nickname == name }.sortedBy { it.datetime.dayOfMonth }

    companion object {
        private const val ATTENDANCE_PATH = "src/main/resources/attendances.csv"
        private const val ATTENDANCE_DATE_FORMAT = "yyyy-MM-dd HH:mm"
        private const val FILE_ATTRIBUTE_LINE = 1
    }
}