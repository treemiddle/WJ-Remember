package com.jay.wj_remember.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.jay.wj_remember.R
import com.jay.wj_remember.databinding.ActivityMainBinding
import com.jay.wj_remember.ui.base.BaseActivity
import com.jay.wj_remember.ui.main.navigator.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    @Inject lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator.setMain()
    }

    override fun setupBinding() {
        binding.vm = viewModel
    }

    override fun setupObserving() = with(viewModel) {
        fragmentType.observe(this@MainActivity, { type ->
            navigator.navigateTo(type)
        })
    }

}