package com.sbaygildin.promoshop

import android.app.Application
import com.sbaygildin.promoshop.core.logging.TimberInitializer
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class PromoShopApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TimberInitializer.init(BuildConfig.DEBUG)
    }
}
