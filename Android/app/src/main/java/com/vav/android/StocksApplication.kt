package com.vav.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StocksApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}