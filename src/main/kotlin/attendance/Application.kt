package attendance

import attendance.controller.check_records.CheckAttendanceRecordsController
import attendance.controller.check_risk_expulsion.CheckRiskOfExpulsionController
import attendance.controller.confirmation.AttendanceConfirmationController
import attendance.controller.home.HomeController
import attendance.controller.update.AttendanceUpdateController
import attendance.domain.service.AttendanceService
import attendance.domain.validator.DateValidator

fun main() {
    val homeController = HomeController()
    val attendanceService = AttendanceService()
    val dateValidator = DateValidator()
    val attendanceConfirmationController = AttendanceConfirmationController(attendanceService, dateValidator)
    val attendanceUpdateController = AttendanceUpdateController(attendanceService, dateValidator)
    val checkAttendanceRecordsController = CheckAttendanceRecordsController(attendanceService)
    val checkRiskOfExpulsionController = CheckRiskOfExpulsionController(attendanceService)
    val appCoordinator = AppCoordinator(
        homeController,
        attendanceConfirmationController,
        attendanceUpdateController,
        checkAttendanceRecordsController,
        checkRiskOfExpulsionController
    )
    appCoordinator.start()
}
