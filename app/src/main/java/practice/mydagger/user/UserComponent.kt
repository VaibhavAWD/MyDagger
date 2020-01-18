package practice.mydagger.user

import dagger.Subcomponent
import practice.mydagger.main.MainActivity
import practice.mydagger.settings.SettingsActivity

@LoggedUserScope
@Subcomponent
interface UserComponent {

    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)
    fun inject(activity: SettingsActivity)
}