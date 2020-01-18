package practice.mydagger.registration

import dagger.Subcomponent
import practice.mydagger.di.ActivityScope
import practice.mydagger.registration.enterdetails.EnterDetailsFragment
import practice.mydagger.registration.termsandconditions.TermsAndConditionsFragment

// Definition of a Dagger subcomponent
@ActivityScope
@Subcomponent
interface RegistrationComponent {

    // Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: RegistrationActivity)

    fun inject(fragment: EnterDetailsFragment)
    fun inject(fragment: TermsAndConditionsFragment)
}