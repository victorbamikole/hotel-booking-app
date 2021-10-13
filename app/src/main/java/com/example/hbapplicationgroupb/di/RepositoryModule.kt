package com.example.hbapplicationgroupb.di

import com.example.hbapplicationgroupb.dataBase.dao.GetAllHotelsItemDao
import com.example.hbapplicationgroupb.model.api.NetworkCall
import com.example.hbapplicationgroupb.repository.ApiToRoomRepositoryImpl
import com.example.hbapplicationgroupb.repository.ApiToRoomRepositoryInterface
import com.example.hbapplicationgroupb.repository.RoomToUIRepositoryImpl
import com.example.hbapplicationgroupb.repository.RoomToUiRepositoryInterface
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
    fun providesApiRepository(networkCall: NetworkCall): ApiToRoomRepositoryInterface {
        return ApiToRoomRepositoryImpl(networkCall)
    }

    @Provides
    @Singleton
    fun providesUIRepository(networkCall : NetworkCall, dao: GetAllHotelsItemDao): RoomToUiRepositoryInterface {
        return RoomToUIRepositoryImpl(networkCall, dao)
    }



}