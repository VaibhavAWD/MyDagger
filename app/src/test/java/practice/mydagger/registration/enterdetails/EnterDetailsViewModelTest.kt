package practice.mydagger.registration.enterdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import practice.mydagger.LiveDataTestUtil

/**
 * Unit tests for implementation of [EnterDetailsViewModel].
 */
class EnterDetailsViewModelTest {

    // SUT
    private lateinit var enterDetailsViewModel: EnterDetailsViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // test user data
    private val testUsername = "testUsername"
    private val testInvalidUsername = "user"
    private val testPassword = "testPassword"
    private val testInvalidPassword = "pass"

    @Before
    fun setUp() {
        enterDetailsViewModel = EnterDetailsViewModel()
    }

    /**
     * validate input gives error when username is invalid
     */
    @Test
    fun `ValidateInput gives error when username is invalid`() {
        // WHEN - validating input with invalid username
        enterDetailsViewModel.validateInput(testInvalidUsername, testPassword)

        // THEN - verify that it gives error
        assertThat(LiveDataTestUtil.getValue(enterDetailsViewModel.enterDetailsState))
            .isInstanceOf(EnterDetailsError::class.java)
    }

    /**
     * validate input gives error when password is invalid
     */
    @Test
    fun `ValidateInput gives error when password is invalid`() {
        // WHEN - validating input with invalid password
        enterDetailsViewModel.validateInput(testUsername, testInvalidPassword)

        // THEN - verify that it gives error
        assertThat(LiveDataTestUtil.getValue(enterDetailsViewModel.enterDetailsState))
            .isInstanceOf(EnterDetailsError::class.java)
    }

    /**
     * validate input succeeds when input is valid
     */
    @Test
    fun `ValidateInput succeeds when input is valid`() {
        // WHEN - validating input with valid data
        enterDetailsViewModel.validateInput(testUsername, testPassword)

        // THEN - verify that it succeeds
        assertThat(LiveDataTestUtil.getValue(enterDetailsViewModel.enterDetailsState))
            .isInstanceOf(EnterDetailsSuccess::class.java)
    }
}