package practice.mydagger.registration

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import practice.mydagger.user.UserManager

/**
 * Unit tests for implementation of [RegistrationViewModel].
 */
@RunWith(MockitoJUnitRunner::class)
class RegistrationViewModelTest {

    // SUT
    private lateinit var registrationViewModel: RegistrationViewModel

    @Mock
    private lateinit var mockUserManager: UserManager

    // test user data
    private val testUsername = "testUsername"
    private val testPassword = "testPassword"

    @Before
    fun setUp() {
        registrationViewModel = RegistrationViewModel(mockUserManager)
    }

    @Test
    fun `Register user calls user manager`() {
        registrationViewModel.updateUserData(testUsername, testPassword)
        registrationViewModel.acceptTCs()
        registrationViewModel.registerUser()
        verify(mockUserManager).registerUser(testUsername, testPassword)
    }
}