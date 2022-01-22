package com.jay.wj_remember.utils.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jay.common.makeLog
import com.jay.wj_remember.ui.base.BaseListAdapter

@BindingAdapter("setUserList")
fun <T> RecyclerView.bindUserList(users: List<T>?) {
    itemAnimator = null

    if (this.adapter is BaseListAdapter<*>) {
        val adapter = this.adapter as BaseListAdapter<T>
        users?.let { adapter.submitList(it) }
        makeLog(javaClass.simpleName, "size: ${users?.size}")
    }
}