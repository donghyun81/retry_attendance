package attendance

import attendance.controller.*
import attendance.domain.AttendanceService
import attendance.domain.DateValidator

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
