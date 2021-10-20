package com.example.hbapplicationgroupb.di


import com.example.hbapplicationgroupb.dataBase.db.HBDataBase
import com.example.hbapplicationgroupb.model.api.HotelServices
import com.example.hbapplicationgroupb.repository.*
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
    fun providesApiRepository(hotelServices: HotelServices): ApiRepositoryInterface {
        return ApiRepositoryImpl(hotelServices)
    }


    @Provides
    @Singleton

    fun providesUIRepository(db: HBDataBase): UiRepositoryInterface {
        return UIRepositoryImpl(db)

    }

//    @Provides
//    @Singleton
//    fun providesDbRepository(db: HBDataBase): DbRepositoryInterface {
//        return DbRepositoryImpl(db)
//    }



}