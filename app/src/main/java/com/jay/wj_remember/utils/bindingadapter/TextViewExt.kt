package com.jay.wj_remember.utils.bindingadapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jay.wj_remember.model.User

@BindingAdapter("setEmptyUserList")
fun TextView.bindEmptyUserList(userList: List<User>?) {
    visibility = if (userList.isNullOrEmpty()) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}