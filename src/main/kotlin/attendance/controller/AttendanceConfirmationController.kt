package attendance.controller

import attendance.domain.AttendanceService
import attendance.view.AttendanceConfirmationInputView

class AttendanceConfirmationController(
    private val attendanceService: AttendanceService
) {
    private val inputView = AttendanceConfirmationInputView()

    fun run() {
        val nickName = getNickName(inputView.readAttendanceName())
        val arrivalTime = getArrivalTime(inputView.readArrivalTime())

    }

    private fun getNickName(name: String) {
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
}