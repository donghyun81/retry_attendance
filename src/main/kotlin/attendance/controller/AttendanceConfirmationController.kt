package attendance.controller

import attendance.domain.AttendanceService
import attendance.view.AttendanceConfirmationInputView

class AttendanceConfirmationController(
    private val attendanceService: AttendanceService
) {
    private val inputView = AttendanceConfirmationInputView()

    fun run() {
        val nickName = inputView.readAttendanceName()
        validateNickName(nickName)
    }

    private fun validateNickName(name: String) {
        val containsCrewName = attendanceService.getCrewNames().contains(name)
        require(containsCrewName) { "[ERROR] 등록되지 않은 닉네임입니다." }
    }
}