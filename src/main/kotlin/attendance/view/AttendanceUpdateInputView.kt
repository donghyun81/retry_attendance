package attendance.view

import camp.nextstep.edu.missionutils.Console

class AttendanceUpdateInputView {
    fun readUpdateName(): String {
        println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.")
        return Console.readLine()
    }

    fun readUpdateDate(): String {
        println("수정하려는 날짜(일)를 입력해 주세요.")
        return Console.readLine()
    }

    fun readUpdateTime(): String {
        println("언제로 변경하겠습니까?")
        return Console.readLine()
    }
}