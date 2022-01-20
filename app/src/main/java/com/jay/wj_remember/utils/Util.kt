package com.jay.wj_remember.utils

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}
