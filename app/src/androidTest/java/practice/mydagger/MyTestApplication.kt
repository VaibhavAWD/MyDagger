package practice.mydagger

import practice.mydagger.di.AppComponent
import practice.mydagger.di.DaggerTestAppComponent

/**
 * [MyTestApplication] will override [MyApplication] in android tests.
 */
class MyTestApplication : MyApplication() {

    override fun initializeComponent(): AppComponent {
        // Creates a new TestAppComponent that injects fake types
        return DaggerTestAppComponent.create()
    }
}