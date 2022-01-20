package com.jay.common

import android.util.Log

fun makeLog(simpleName: String, message: String) {
    if (BuildConfig.DEBUG) Log.d(simpleName, message)
}