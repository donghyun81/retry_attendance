package attendance

import attendance.controller.AppCoordinator
import attendance.controller.AttendanceConfirmationController
import attendance.controller.HomeController
import attendance.domain.AttendanceService
import attendance.domain.DateValidator

fun main() {
    val homeController = HomeController()
    val attendanceService = AttendanceService()
    val dateValidator = DateValidator()
    val attendanceConfirmationController = AttendanceConfirmationController(attendanceService, dateValidator)
    val appCoordinator = AppCoordinator(homeController, attendanceConfirmationController)
    appCoordinator.start()
}
