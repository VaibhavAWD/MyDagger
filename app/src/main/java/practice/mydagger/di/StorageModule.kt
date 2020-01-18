package practice.mydagger.di

import android.content.Context
import dagger.Module
import dagger.Provides
import practice.mydagger.storage.SharedPreferencesStorage
import practice.mydagger.storage.Storage

// Tells Dagger this is a Dagger module
@Module
class StorageModule {

    // @Provides tell Dagger how to create instances of the type that this function
    // returns (i.e. Storage).
    // Function parameters are the dependencies of this type (i.e. Context).
    @Provides
    fun provideStorage(context: Context): Storage {
        // Whenever Dagger needs to provide an instance of type Storage,
        // this code (the one inside the @Provides method) will be run.
        return SharedPreferencesStorage(context)
    }
}