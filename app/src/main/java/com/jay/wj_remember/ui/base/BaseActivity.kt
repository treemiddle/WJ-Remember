package com.jay.wj_remember.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB : ViewDataBinding>(
    @LayoutRes
    val layoutResId: Int
) : AppCompatActivity() {

    protected val binding by lazy { DataBindingUtil.setContentView<VDB>(this, layoutResId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply { lifecycleOwner = this@BaseActivity }

        setupBinding()
        setupObserving()
    }

    abstract fun setupBinding()

    abstract fun setupObserving()

}