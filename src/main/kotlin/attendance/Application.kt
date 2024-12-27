package attendance

import attendance.di.AppContainer
import attendance.ui.check_records.CheckAttendanceRecordsController
import attendance.ui.check_risk_expulsion.CheckRiskOfExpulsionController
import attendance.ui.confirmation.AttendanceConfirmationController
import attendance.ui.home.HomeController
import attendance.ui.update.AttendanceUpdateController
import attendance.domain.service.AttendanceService
import attendance.domain.validator.DateValidator

fun main() {
    val appCoordinator = AppContainer().appCoordinator
    appCoordinator.start()
}
