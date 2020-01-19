package practice.mydagger.main

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever
import org.mockito.junit.MockitoJUnitRunner
import practice.mydagger.user.UserDataRepository

/**
 * Unit tests for implementation of [MainViewModel].
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    // SUT
    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var mockUserDataRepository: UserDataRepository

    // test user data
    private val testUsername = "testUsername"
    private val testUnreadNotifications = 5

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(mockUserDataRepository)
    }

    @Test
    fun `Welcome text returns correct text`() {
        // mock username
        whenever(mockUserDataRepository.username).thenReturn(testUsername)

        // WHEN - getting welcome text
        val welcomeText = mainViewModel.welcomeText

        // THEN - verify that the welcome text has expected value
        assertThat(welcomeText).isEqualTo("Hello $testUsername")
    }

    @Test
    fun `Notification text returns correct text`() {
        // mock notifications count
        whenever(mockUserDataRepository.unreadNotifications)
            .thenReturn(testUnreadNotifications)

        // WHEN - getting notification text
        val notificationText = mainViewModel.notificationsText

        // THEN - verify that the notification text has expected value
        assertThat(notificationText).isEqualTo(
            "You have $testUnreadNotifications unread notifications"
        )
    }

}