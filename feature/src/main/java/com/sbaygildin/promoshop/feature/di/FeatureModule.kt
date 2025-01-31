package com.sbaygildin.promoshop.feature.di

import com.sbaygildin.promoshop.domain.repository.AdressRepository
import com.sbaygildin.promoshop.domain.usecase.SearchAdressUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FeatureModule {
    @Provides
    fun provideSearchAdressUseCase(repository: AdressRepository): SearchAdressUseCase{
        return SearchAdressUseCase(repository)
    }
}

