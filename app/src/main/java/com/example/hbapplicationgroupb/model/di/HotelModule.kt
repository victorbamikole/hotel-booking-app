package com.example.hbapplicationgroupb.model.di

import com.example.hbapplicationgroupb.model.api.RetrofitServiceInterface
import com.example.hbapplicationgroupb.model.constants.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HotelModule {

    @Singleton
    @Provides
    fun provideDataInHotelModule(): RetrofitServiceInterface {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level= HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RetrofitServiceInterface::class.java)

    }
}