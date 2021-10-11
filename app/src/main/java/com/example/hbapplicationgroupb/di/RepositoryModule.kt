package com.example.hbapplicationgroupb.di

import com.example.hbapplicationgroupb.model.api.ApiServices
import com.example.hbapplicationgroupb.repository.ApiRepositoryImpl
import com.example.hbapplicationgroupb.repository.ApiRepositoryInterface
import com.example.hbapplicationgroupb.repository.UIRepositoryImpl
import com.example.hbapplicationgroupb.repository.UiRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesApiRepository(apiServices: ApiServices): ApiRepositoryInterface {
        return ApiRepositoryImpl(apiServices)
    }

    @Provides
    @Singleton
    fun providesUIRepository(): UiRepositoryInterface {
        return UIRepositoryImpl()
    }



}