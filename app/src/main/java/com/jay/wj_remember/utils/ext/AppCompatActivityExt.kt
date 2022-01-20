package com.jay.wj_remember.utils.ext

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.removeAnimation() {
    this.overridePendingTransition(0, 0)
}