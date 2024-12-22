package attendance.view

import camp.nextstep.edu.missionutils.Console
import common.formatTwoDigit
import common.getDayOfWeek
import java.time.LocalDateTime

class HomeInputView {

    fun readUserAction(today: LocalDateTime): String {
        println("오늘은 ${today.monthValue}월 ${today.dayOfMonth.formatTwoDigit()}일 ${today.dayOfWeek.getDayOfWeek()}입니다. 기능을 선택해 주세요.")
        println(UserAction.ATTENDANCE_CONFIRMATION.message)
        println(UserAction.ATTENDANCE_CORRECTION.message)
        println(UserAction.CHECK_ATTENDANCE_RECORDS.message)
        println(UserAction.CHECK_RISK_OF_EXPULSION.message)
        println(UserAction.END.message)
        return Console.readLine()
    }
}