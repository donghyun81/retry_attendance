package attendance.view

import camp.nextstep.edu.missionutils.Console

class CheckAttendanceRecordsInputView {
    fun readQueryName(): String {
        println("닉네임을 입력해 주세요.")
        return Console.readLine()
    }
}
