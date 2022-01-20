package com.jay.wj_remember.utils.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jay.common.makeLog
import com.jay.wj_remember.ui.base.BaseListAdapter

@BindingAdapter("setUserList")
fun <T> RecyclerView.bindUserList(users: List<T>?) {
    if (adapter is BaseListAdapter<*>) {
        val adapter = adapter as BaseListAdapter<T>
        adapter.submitList(users)
        makeLog(javaClass.simpleName, "size: ${users?.size}")
    }
}