package practice.mydagger.settings

import practice.mydagger.user.UserDataRepository
import practice.mydagger.user.UserManager

class SettingsViewModel(
    private val userDataRepository: UserDataRepository,
    private val userManager: UserManager
) {

    fun refreshNotifications() {
        userDataRepository.refreshUnreadNotifications()
    }

    fun logout() {
        userManager.logout()
    }
}