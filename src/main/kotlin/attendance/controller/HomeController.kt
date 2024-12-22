package attendance.controller

import attendance.view.HomeInputView
import attendance.view.UserAction

class HomeController {
    private val inputView = HomeInputView()
    fun run() {
        val userActionInput = inputView.readUserAction()
        val userAction = convertUserAction(userActionInput)
    }

    private fun convertUserAction(input: String): UserAction {
        val userAction = UserAction.entries.find { it.type == input }
        return requireNotNull(userAction) { "[ERROR] 잘못된 형식을 입력하였습니다." }
    }
}