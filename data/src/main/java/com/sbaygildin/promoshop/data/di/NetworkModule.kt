package com.sbaygildin.promoshop.data.di

import com.sbaygildin.promoshop.data.api.DadataApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideDadataApi(apiKey: String): DadataApi {
        return Retrofit.Builder()
            .baseUrl("https://suggestions.dadata.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor(apiKey))
                    .build()
            )
            .build()
            .create(DadataApi::class.java)
    }
}

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Token $apiKey")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }
}