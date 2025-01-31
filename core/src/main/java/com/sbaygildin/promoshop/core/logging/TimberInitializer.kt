package com.sbaygildin.promoshop.core.logging

import timber.log.Timber

object TimberInitializer {
    fun init(isDebug: Boolean) {
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
