package com.example.hbapplicationgroupb.di

import android.content.Context
import androidx.room.Room
import com.example.hbapplicationgroupb.dataBase.db.HBDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Volatile
    private var INSTANCE : HBDataBase? = null

    @Singleton
    @Provides
    fun getDataBase(@ApplicationContext context: Context): HBDataBase {
        val tempInstance = INSTANCE
        if(tempInstance!=null){
            return tempInstance
        }
        synchronized(this){
            val instance = Room.databaseBuilder(context.applicationContext,
                HBDataBase::class.java,
                "app_database").build()
            INSTANCE = instance
            return instance
        }
    }

}