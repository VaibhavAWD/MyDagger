package practice.mydagger.user

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import practice.mydagger.storage.FakeStorage
import practice.mydagger.storage.Storage

/**
 * Unit tests for implementation of [UserManager]
 */
class UserManagerTest {

    // SUT
    private lateinit var userManager: UserManager

    // Use Storage to be injected into UserManager
    private lateinit var storage: Storage

    // test user data
    private val testUsername = "testUsername"
    private val testInvalidUsername = "testInvalidUsername"
    private val testPassword = "testPassword"
    private val testInvalidPassword = "testInvalidPassword"

    companion object {
        private const val REGISTERED_USER = "registered_user"
        private const val PASSWORD_SUFFIX = "password"
    }

    @Before
    fun setUp() {
        val userComponentFactory = mock(UserComponent.Factory::class.java)
        val userComponent = mock(UserComponent::class.java)
        `when`(userComponentFactory.create()).thenReturn(userComponent)

        storage = FakeStorage()
        userManager = UserManager(storage, userComponentFactory)
    }

    @Test
    fun `Username returns what is in the storage`() {
        // GIVEN - user is not registered
        assertThat(userManager.username).isEmpty()

        // WHEN - registering user
        userManager.registerUser(testUsername, testPassword)

        // THEN - verify that the username has expected value
        assertThat(userManager.username).isEqualTo(testUsername)
    }

    @Test
    fun `IsUserRegistered behaves as expected`() {
        // GIVEN - user is not registered
        assertThat(userManager.isUserRegistered()).isFalse()

        // WHEN - registering user
        userManager.registerUser(testUsername, testPassword)

        // THEN - verify that the user is registered
        assertThat(userManager.isUserRegistered()).isTrue()
    }

    @Test
    fun `Register user adds username and password to the storage`() {
        // GIVEN - user is not registered
        assertThat(userManager.isUserRegistered()).isFalse()
        assertThat(userManager.isUserLoggedIn()).isFalse()

        // WHEN - registering user
        userManager.registerUser(testUsername, testPassword)

        // THEN - verify that the registered user has expected values
        assertThat(userManager.isUserRegistered()).isTrue()
        assertThat(userManager.isUserLoggedIn()).isTrue()
        assertThat(storage.getString(REGISTERED_USER)).isEqualTo(testUsername)
        assertThat(storage.getString("${userManager.username}$PASSWORD_SUFFIX"))
            .isEqualTo(testPassword)
    }

    @Test
    fun `Login succeeds when username is registered and password is correct`() {
        // GIVEN - user is registered
        userManager.registerUser(testUsername, testPassword)
        userManager.logout() // logout in order to perform login

        // WHEN - user logs in
        assertThat(userManager.loginUser(testUsername, testPassword)).isTrue()

        // THEN - verify that the user is logged in
        assertThat(userManager.isUserLoggedIn()).isTrue()
    }

    @Test
    fun `Login fails when username is not registered`() {
        // GIVEN - user is registered
        userManager.registerUser(testUsername, testPassword)
        userManager.logout()

        // WHEN - user logs in with invalid username
        assertThat(userManager.loginUser(testInvalidUsername, testPassword)).isFalse()

        // THEN - verify that the user is not logged in
        assertThat(userManager.isUserLoggedIn()).isFalse()
    }

    @Test
    fun `Login fails when username registered but password is incorrect`() {
        // GIVEN - user is registered
        userManager.registerUser(testUsername, testPassword)
        userManager.logout()

        // WHEN - user logs in with incorrect password
        assertThat(userManager.loginUser(testUsername, testInvalidPassword)).isFalse()

        // THEN - verify that the user is not logged in
        assertThat(userManager.isUserLoggedIn()).isFalse()
    }

    @Test
    fun `Unregister behaves as expected`() {
        // GIVEN  - user is registered
        userManager.registerUser(testUsername, testPassword)
        assertThat(userManager.isUserLoggedIn()).isTrue()

        // WHEN - user unregisters
        userManager.unregister()

        // THEN - verify that the is not logged in and is not registered
        assertThat(userManager.isUserLoggedIn()).isFalse()
        assertThat(userManager.isUserRegistered()).isFalse()
    }
}