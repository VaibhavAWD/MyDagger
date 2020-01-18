package practice.mydagger.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import practice.mydagger.login.LoginComponent
import practice.mydagger.main.MainActivity
import practice.mydagger.registration.RegistrationActivity
import practice.mydagger.registration.RegistrationComponent
import practice.mydagger.registration.enterdetails.EnterDetailsFragment
import practice.mydagger.registration.termsandconditions.TermsAndConditionsFragment
import practice.mydagger.settings.SettingsActivity
import practice.mydagger.user.UserManager
import javax.inject.Singleton

// Definition of a Dagger Component that adds info from the StorageModule to the graph
@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Expose UserManager so that MainActivity and SettingsActivity
    // can access a particular instance of UserComponent
    fun userManager(): UserManager

    // Types that can be retrieved from the graph
    fun registrationComponent(): RegistrationComponent.Factory

    fun loginComponent(): LoginComponent.Factory
}