package com.example.hbapplicationgroupb.di


import com.example.hbapplicationgroupb.dataBase.db.HBDataBase
import com.example.hbapplicationgroupb.model.api.HotelServices
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
    fun providesApiRepository(hotelServices: HotelServices, db :HBDataBase): ApiRepositoryInterface {
        return ApiRepositoryImpl(hotelServices)
    }

    @Provides
    @Singleton

    fun providesUIRepository(db: HBDataBase): UiRepositoryInterface {
        return UIRepositoryImpl(db)

    }



}