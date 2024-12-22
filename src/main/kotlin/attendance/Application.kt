package attendance

import attendance.controller.AppCoordinator
import attendance.controller.HomeController

fun main() {
    val homeController = HomeController()
    val appCoordinator = AppCoordinator(homeController)
    appCoordinator.start()
}
