package practice.mydagger.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever
import org.mockito.junit.MockitoJUnitRunner
import practice.mydagger.LiveDataTestUtil
import practice.mydagger.user.UserManager

/**
 * Unit tests for implementation of [LoginViewModel].
 */
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    // SUT
    private lateinit var loginViewModel: LoginViewModel

    @Mock
    private lateinit var mockUserManager: UserManager

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // test user data
    private val testUsername = "testUsername"

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(mockUserManager)
    }

    @Test
    fun `Get username`() {
        // mock username
        whenever(mockUserManager.username).thenReturn(testUsername)

        // WHEN - getting username
        val username = loginViewModel.getUsername()

        // THEN - verify that the username has expected value
        assertThat(username).isEqualTo(testUsername)
    }

    @Test
    fun `Login emits success`() {
        // mock user login that returns success
        whenever(mockUserManager.loginUser(anyString(), anyString()))
            .thenReturn(true)

        // WHEN - attempting login
        loginViewModel.login(anyString(), anyString())

        // THEN - verify that login was successful
        assertThat(LiveDataTestUtil.getValue(loginViewModel.loginState))
            .isInstanceOf(LoginSuccess::class.java)
    }

    @Test
    fun `Login emits error`() {
        // mock user login that returns failure
        whenever(mockUserManager.loginUser(anyString(), anyString()))
            .thenReturn(false)

        // WHEN - attempting login
        loginViewModel.login(anyString(), anyString())

        // THEN - verify that user login was not successful
        assertThat(LiveDataTestUtil.getValue(loginViewModel.loginState))
            .isInstanceOf(LoginError::class.java)
    }
    
    @Test
    fun `Login unregisters calls user manager`() {
        // WHEN - unregistering user
        loginViewModel.unregister()

        // THEN - verify that the method is called from user manager
        verify(mockUserManager).unregister()
    }
}