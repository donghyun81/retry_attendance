package attendance.controller

import attendance.domain.AttendanceService
import attendance.view.AttendanceConfirmationInputView
import java.time.LocalDate

class AttendanceConfirmationController(
    private val attendanceService: AttendanceService
) {
    private val inputView = AttendanceConfirmationInputView()
    private val today = LocalDate.now()

    fun run() {
        val nickName = inputView.readAttendanceName()
        validateNickName(nickName)
        validateAttendance(nickName)
        val arrivalTime = getArrivalTime(inputView.readArrivalTime())
    }

    private fun validateNickName(name: String){
        val containsCrewName = attendanceService.getCrewNames().contains(name)
        require(containsCrewName) { "[ERROR] 등록되지 않은 닉네임입니다." }
    }

    private fun getArrivalTime(time: String): Pair<Int, Int> {
        require(time.split(":").size == 2)
        val (hourInput, minuteInput) = time.split(":")
        val hour = requireNotNull(hourInput.toIntOrNull()) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        val minute = requireNotNull(minuteInput.toIntOrNull()) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(hour in 0..23) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        require(minute in 0..59) { "[ERROR] 잘못된 형식을 입력하였습니다." }
        return Pair(hour, minute)
    }

    private fun validateAttendance(name: String) {
        val hasAttendance = attendanceService.hasAttendance(name, today.dayOfMonth)
        require(hasAttendance) { "[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요." }
    }
}