package attendance.view

import camp.nextstep.edu.missionutils.Console

class HomeInputView {

    fun readUserAction(): String {
        println("오늘은 12월 13일 금요일입니다. 기능을 선택해 주세요.")
        println(UserAction.ATTENDANCE_CONFIRMATION.message)
        println(UserAction.ATTENDANCE_CORRECTION.message)
        println(UserAction.CHECK_ATTENDANCE_RECORDS.message)
        println(UserAction.CONFIRMATION_OF_THOSE_AT_RISK_OF_EXPULSION.message)
        println(UserAction.END.message)
        return Console.readLine()
    }
}