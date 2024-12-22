package attendance

import attendance.controller.check_records.CheckAttendanceRecordsController
import attendance.controller.check_risk_expulsion.CheckRiskOfExpulsionController
import attendance.controller.confirmation.AttendanceConfirmationController
import attendance.controller.home.HomeController
import attendance.controller.update.AttendanceUpdateController
import common.UserAction

class AppCoordinator(
    private val homeController: HomeController,
    private val attendanceConfirmationController: AttendanceConfirmationController,
    private val attendanceUpdateController: AttendanceUpdateController,
    private val checkAttendanceRecordsController: CheckAttendanceRecordsController,
    private val checkRiskOfExpulsionController: CheckRiskOfExpulsionController
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
                attendanceUpdateController.run()
                true
            }

            UserAction.CHECK_ATTENDANCE_RECORDS -> {
                checkAttendanceRecordsController.run()
                true
            }

            UserAction.CHECK_RISK_OF_EXPULSION -> {
                checkRiskOfExpulsionController.run()
                true
            }

            UserAction.END -> false
        }
    }
}