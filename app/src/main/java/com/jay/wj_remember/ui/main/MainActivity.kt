package com.jay.wj_remember.ui.main

import androidx.activity.viewModels
import com.jay.wj_remember.R
import com.jay.wj_remember.databinding.ActivityMainBinding
import com.jay.wj_remember.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserving() = with(binding) {

    }

}