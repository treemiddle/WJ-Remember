package com.jay.wj_remember.utils.bindingadapter

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import com.jay.wj_remember.ui.main.MainViewModel

@BindingAdapter("setEditQueryListener")
fun EditText.bindEditQueryListener(vm: MainViewModel?) {
    addTextChangedListener { vm?.debounceQuery(it.toString().trim()) }
}