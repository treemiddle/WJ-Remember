package com.jay.wj_remember.utils.bindingadapter

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout
import com.jay.wj_remember.R
import com.jay.wj_remember.ui.main.MainViewModel

@BindingAdapter("setEditQueryListener")
fun EditText.bindEditQueryListener(vm: MainViewModel?) {
    addTextChangedListener { vm?.debounceQuery(it.toString().trim()) }
}

@BindingAdapter("setTabItemListener")
fun TabLayout.bindTabItemListener(vm: MainViewModel?) {
    for (i in 0..1) {
        val tab: TabLayout.Tab = if (i == 0) {
            this.newTab().setText(R.string.main_tab_01)
        } else {
            this.newTab().setText(R.string.main_tab_02)
        }

        addTab(tab)
    }

    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
        override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        override fun onTabSelected(tab: TabLayout.Tab?) {
            vm?.setTabPosition(tab?.position)
        }
    })
}