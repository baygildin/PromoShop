package com.sbaygildin.promoshop.core.logging



import timber.log.Timber

object EventLogger {

    fun logClick(eventName: String) {
        Timber.d("Click event: $eventName")
    }
    fun logScreenView(screenName: String) {
        Timber.d("Screen opened: $screenName")
    }
    fun logError(message: String, throwable: Throwable? = null) {
        Timber.e(throwable, "Error: $message")
    }
}
