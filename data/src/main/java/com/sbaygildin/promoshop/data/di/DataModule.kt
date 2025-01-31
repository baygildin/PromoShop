package com.sbaygildin.promoshop.data.di

import com.sbaygildin.promoshop.data.repository.AdressRepositoryImpl
import com.sbaygildin.promoshop.domain.repository.AdressRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindAdressRepository(impl : AdressRepositoryImpl): AdressRepository
}
