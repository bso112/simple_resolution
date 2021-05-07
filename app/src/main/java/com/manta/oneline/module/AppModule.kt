package com.manta.oneline.module

import android.app.Application
import com.manta.data.repository.MainRepository
import com.manta.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(context : Application) : Repository = MainRepository(context)

}