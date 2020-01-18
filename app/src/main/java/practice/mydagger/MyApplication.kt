package practice.mydagger

import android.app.Application
import practice.mydagger.storage.SharedPreferencesStorage
import practice.mydagger.user.UserManager

open class MyApplication : Application() {

    open val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }
}