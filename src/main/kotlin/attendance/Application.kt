package attendance

import attendance.controller.AppCoordinator
import attendance.controller.AttendanceConfirmationController
import attendance.controller.AttendanceUpdateController
import attendance.controller.HomeController
import attendance.domain.AttendanceService
import attendance.domain.DateValidator

fun main() {
    val homeController = HomeController()
    val attendanceService = AttendanceService()
    val dateValidator = DateValidator()
    val attendanceConfirmationController = AttendanceConfirmationController(attendanceService, dateValidator)
    val attendanceUpdateController = AttendanceUpdateController(attendanceService, dateValidator)
    val appCoordinator = AppCoordinator(homeController, attendanceConfirmationController, attendanceUpdateController)
    appCoordinator.start()
}
