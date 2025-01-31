package com.sbaygildin.promoshop.di


import com.sbaygildin.promoshop.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {
    @Provides
    @Singleton
    fun provideDadataApiKey(): String = BuildConfig.DADATA_API_KEY
}
