package practice.mydagger.di

import dagger.Binds
import dagger.Module
import practice.mydagger.storage.SharedPreferencesStorage
import practice.mydagger.storage.Storage

// Tells Dagger this is a Dagger module
// Because of @Binds, StorageModule needs to be an abstract class
@Module
abstract class StorageModule {

    // Makes Dagger provide SharedPreferencesStorage when a Storage type is requested
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}