package attendance

import attendance.controller.AppCoordinator
import attendance.controller.AttendanceConfirmationController
import attendance.controller.HomeController
import attendance.domain.AttendanceService

fun main() {
    val homeController = HomeController()
    val attendanceService = AttendanceService()
    val attendanceConfirmationController = AttendanceConfirmationController(attendanceService)
    val appCoordinator = AppCoordinator(homeController, attendanceConfirmationController)
    appCoordinator.start()
}
