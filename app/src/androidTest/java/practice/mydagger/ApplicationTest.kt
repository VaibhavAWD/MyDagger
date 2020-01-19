package practice.mydagger

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import practice.mydagger.main.MainActivity

class ApplicationTest {

    // test user data
    private val testUsername = "testUsername"
    private val testPassword = "testPassword"

    @Test
    fun runApp() {
        ActivityScenario.launch(MainActivity::class.java)

        // Should be in Registration/EnterDetails because the user is not registered
        onView(withText(R.string.registration_dagger)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_next)).perform(click())
        onView(withText("Username has to be longer than 4 characters"))
            .check(matches(isDisplayed()))
        onView(withId(R.id.edt_username)).perform(typeText(testUsername), closeSoftKeyboard())
        onView(withId(R.id.btn_next)).perform(click())
        onView(withText("Password has to be longer than 4 characters"))
            .check(matches(isDisplayed()))
        onView(withId(R.id.edt_password)).perform(typeText(testPassword), closeSoftKeyboard())
        onView(withId(R.id.btn_next)).perform(click())

        // Registration/T&Cs
        onView(withText(R.string.terms_and_conditions)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_register)).perform(click())

        // Main
        onView(withText("Hello $testUsername")).check(matches(isDisplayed()))
        onView(withId(R.id.btn_settings)).perform(click())

        // Settings
        onView(withId(R.id.btn_refresh)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_logout)).perform(click())

        // Login
        onView(withText(R.string.welcome_dagger)).check(matches(isDisplayed()))
        onView(withId(R.id.edt_username)).check(matches(not(isEnabled())))
        onView(withId(R.id.edt_username)).check(matches(withText(testUsername)))
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText(R.string.login_error)).check(matches(isDisplayed()))
        onView(withId(R.id.edt_password)).perform(typeText(testPassword), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())

        // Main
        onView(withText("Hello $testUsername")).check(matches(isDisplayed()))
        onView(withId(R.id.btn_settings)).perform(click())

        // Settings
        onView(withText(R.string.refresh_notifications)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_logout)).perform(click())

        // Login
        onView(withText(R.string.welcome_dagger)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_unregister)).perform(click())

        // Registration
        onView(withText(R.string.registration_dagger)).check(matches(isDisplayed()))
    }

}