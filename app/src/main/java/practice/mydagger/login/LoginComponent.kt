package practice.mydagger.login

import dagger.Subcomponent
import practice.mydagger.di.ActivityScope

@ActivityScope
@Subcomponent
interface LoginComponent {

    // Factory to create instances of LoginComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: LoginActivity)

}