package edu.itvo.pets.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.itvo.pets.PetsApp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {
    @Provides
    @Singleton
    fun provideContext(application: PetsApp): Context {
        return application.applicationContext
    }

}