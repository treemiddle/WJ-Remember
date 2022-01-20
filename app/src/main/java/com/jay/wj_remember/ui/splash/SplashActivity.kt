package com.jay.wj_remember.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.jay.wj_remember.R
import com.jay.wj_remember.databinding.ActivitySplashBinding
import com.jay.wj_remember.ui.base.BaseActivity
import com.jay.wj_remember.ui.main.MainActivity
import com.jay.wj_remember.utils.ext.removeAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 1500L)
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserving() = with(viewModel) {

    }

}