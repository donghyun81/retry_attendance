package attendance.di

import attendance.AppCoordinator
import attendance.domain.service.AttendanceService
import attendance.domain.validator.DateValidator
import attendance.ui.check_records.CheckAttendanceRecordsController
import attendance.ui.check_risk_expulsion.CheckRiskOfExpulsionController
import attendance.ui.confirmation.AttendanceConfirmationController
import attendance.ui.home.HomeController
import attendance.ui.update.AttendanceUpdateController

class AppContainer {
    private val homeController = HomeController()
    private val attendanceService = AttendanceService()
    private val dateValidator = DateValidator()
    private val attendanceConfirmationController = AttendanceConfirmationController(attendanceService, dateValidator)
    private val attendanceUpdateController = AttendanceUpdateController(attendanceService, dateValidator)
    private val checkAttendanceRecordsController = CheckAttendanceRecordsController(attendanceService)
    private val checkRiskOfExpulsionController = CheckRiskOfExpulsionController(attendanceService)

    val appCoordinator = AppCoordinator(
        homeController,
        attendanceConfirmationController,
        attendanceUpdateController,
        checkAttendanceRecordsController,
        checkRiskOfExpulsionController
    )
}