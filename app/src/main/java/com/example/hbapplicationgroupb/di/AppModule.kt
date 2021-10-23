package com.example.hbapplicationgroupb.di

import android.content.Context
import com.example.hbapplicationgroupb.di.application.HotelApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    internal fun provideApplication(@ApplicationContext app: Context): HotelApplication {
        return app as HotelApplication
    }
}