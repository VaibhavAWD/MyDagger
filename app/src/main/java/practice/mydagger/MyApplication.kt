package practice.mydagger

import android.app.Application
import practice.mydagger.di.AppComponent
import practice.mydagger.di.DaggerAppComponent
import practice.mydagger.storage.SharedPreferencesStorage
import practice.mydagger.user.UserManager

open class MyApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }
}