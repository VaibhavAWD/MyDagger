package practice.mydagger.user

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import practice.mydagger.storage.FakeStorage

/**
 * Unit tests for implementation of [UserDataRepository].
 */
@RunWith(MockitoJUnitRunner::class)
class UserDataRepositoryTest {

    // SUT
    private lateinit var userDataRepository: UserDataRepository

    private lateinit var userManager: UserManager

    private lateinit var storage: FakeStorage

    @Mock
    private lateinit var userComponentFactory: UserComponent.Factory

    @Mock
    private lateinit var userComponent: UserComponent

    // test user data
    private val testUsername = "testUsername"
    private val testPassword = "testPassword"
    private val limit = 100

    @Before
    fun setUp() {
        `when`(userComponentFactory.create()).thenReturn(userComponent)
        storage = FakeStorage()
        userManager = UserManager(storage, userComponentFactory)
        userDataRepository = UserDataRepository(userManager)
    }

    @Test
    fun `Username is exposed as is from user manager`() {
        // initially no user is registered
        assertThat(userDataRepository.username).isEmpty()

        // GIVEN - user is registered
        userManager.registerUser(testUsername, testPassword)
        assertThat(userManager.isUserRegistered()).isTrue()

        // WHEN - getting username from user data repository
        val username = userDataRepository.username

        // THEN - verify that the username is the same
        assertThat(username).isEqualTo(testUsername)
    }

    @Test
    fun `On refreshing the unread notifications the value is different`() {
        // GIVEN - initial unread notifications
        val initial = userDataRepository.unreadNotifications

        // WHEN - refreshing
        userDataRepository.refreshUnreadNotifications()

        // THEN - verify that initial and second values are different
        assertThat(userDataRepository.unreadNotifications).isNotEqualTo(initial)
    }

    @Test
    fun `Unread notifications value is never greater than hundred`() {
        userDataRepository.refreshUnreadNotifications()
        assertThat(userDataRepository.unreadNotifications).isLessThan(limit)

        userDataRepository.refreshUnreadNotifications()
        assertThat(userDataRepository.unreadNotifications).isLessThan(limit)

        userDataRepository.refreshUnreadNotifications()
        assertThat(userDataRepository.unreadNotifications).isLessThan(limit)

        userDataRepository.refreshUnreadNotifications()
        assertThat(userDataRepository.unreadNotifications).isLessThan(limit)
    }
}