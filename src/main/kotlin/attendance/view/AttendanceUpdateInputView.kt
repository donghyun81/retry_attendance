package attendance.view

import camp.nextstep.edu.missionutils.Console

class AttendanceUpdateInputView {
    fun readUpdateName(): String {
        println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.")
        return Console.readLine()
    }
}