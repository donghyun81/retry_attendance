package attendance.controller

import attendance.view.UserAction

class AppCoordinator(
    private val homeController: HomeController,
    private val attendanceConfirmationController: AttendanceConfirmationController
) {
    fun start() {
        while (true) {
            val userAction = homeController.getUserAction()
            val isContinue = runUserAction(userAction)
            if (isContinue.not()) break
        }
    }

    private fun runUserAction(userAction: UserAction): Boolean {
        return when (userAction) {
            UserAction.ATTENDANCE_CONFIRMATION -> {
                attendanceConfirmationController.run()
                true
            }

            UserAction.ATTENDANCE_CORRECTION -> {
                true
            }

            UserAction.CHECK_ATTENDANCE_RECORDS -> {
                true
            }

            UserAction.CONFIRMATION_OF_THOSE_AT_RISK_OF_EXPULSION -> {
                true
            }

            UserAction.END -> false
        }
    }
}