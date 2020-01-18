package practice.mydagger.main

import practice.mydagger.user.UserDataRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val userDataRepository: UserDataRepository) {

    val welcomeText: String
        get() = "Hello ${userDataRepository.username}"

    val notificationsText: String
        get() = "You have ${userDataRepository.unreadNotifications} unread notifications"
}