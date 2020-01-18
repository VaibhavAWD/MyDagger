package practice.mydagger.di

import dagger.Module
import practice.mydagger.login.LoginComponent
import practice.mydagger.registration.RegistrationComponent
import practice.mydagger.user.UserComponent

// This module tells AppComponent which are its subcomponents
@Module(
    subcomponents = [
        RegistrationComponent::class,
        LoginComponent::class,
        UserComponent::class
    ]
)
class AppSubcomponents {

}