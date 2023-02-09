package com.artistapp

import android.app.Application
import com.artistapp.common.config.ConfigProvider
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var configProvider: ConfigProvider

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (configProvider.isDebug) Timber.plant(Timber.DebugTree())
    }
}