package practice.mydagger.di

import dagger.Binds
import dagger.Module
import practice.mydagger.storage.FakeStorage
import practice.mydagger.storage.Storage

// Overrides StorageModule in android tests
@Module
abstract class TestStorageModule {

    // Makes Dagger provide FakeStorage when a Storage type is requested in android tests
    @Binds
    abstract fun provideStorage(storage: FakeStorage): Storage
}