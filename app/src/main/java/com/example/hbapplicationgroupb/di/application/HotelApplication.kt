package com.example.hbapplicationgroupb.di.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HotelApplication : Application() {

    companion object {
        @get:Synchronized
        lateinit var application: HotelApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}