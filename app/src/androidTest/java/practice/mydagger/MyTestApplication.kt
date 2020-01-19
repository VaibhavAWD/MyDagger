package practice.mydagger

import practice.mydagger.di.AppComponent

/**
 * [MyTestApplication] will override [MyApplication] in android tests.
 */
class MyTestApplication : MyApplication() {

    override fun initializeComponent(): AppComponent {
        // Creates a new TestAppComponent that injects fake types
        return DaggerTestAppComponent.create()
    }
}