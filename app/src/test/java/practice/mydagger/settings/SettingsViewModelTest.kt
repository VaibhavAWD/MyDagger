package practice.mydagger.settings

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import practice.mydagger.user.UserDataRepository
import practice.mydagger.user.UserManager

/**
 * Unit tests for implementation of [SettingsViewModel].
 */
@RunWith(MockitoJUnitRunner::class)
class SettingsViewModelTest {

    // SUT
    private lateinit var settingsViewModel: SettingsViewModel

    @Mock
    private lateinit var mockUserManager: UserManager

    @Mock
    private lateinit var mockUserDataRepository: UserDataRepository

    @Before
    fun setUp() {
        settingsViewModel = SettingsViewModel(mockUserDataRepository, mockUserManager)
    }

    @Test
    fun `Refresh notifications works as expected`() {
        // WHEN - refreshing notifications
        settingsViewModel.refreshNotifications()

        // THEN - verify that subsequent calls are made from user data repository
        verify(mockUserDataRepository).refreshUnreadNotifications()
    }

    @Test
    fun `Logout works as expected`() {
        // WHEN - attempting logout
        settingsViewModel.logout()

        // THEN - verify that subsequent calls are made from user manager
        verify(mockUserManager).logout()
    }
}