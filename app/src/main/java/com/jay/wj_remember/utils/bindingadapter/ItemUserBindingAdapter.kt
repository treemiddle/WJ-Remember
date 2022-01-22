package com.jay.wj_remember.utils.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.jay.wj_remember.R
import com.jay.wj_remember.utils.ext.loadImage
import com.jay.wj_remember.utils.getProgressDrawable

@BindingAdapter("setUserHeader")
fun bindUserHeader(tv: TextView, header: String?) {
    if (header.isNullOrEmpty()) {
        tv.visibility = View.GONE
        tv.text = null
    } else {
        tv.visibility = View.VISIBLE
        tv.text = header
    }
}

@BindingAdapter("setUserImage")
fun bindUserImage(iv: ImageView, image: String?) {
    iv.loadImage(image, getProgressDrawable(iv.context))
}

@BindingAdapter("setUserLike")
fun bindUserLike(iv: ImageView, hasLiked: Boolean) {
    if (hasLiked) {
        iv.background = ContextCompat.getDrawable(iv.context, R.drawable.ic_like_star)
    } else {
        iv.background = ContextCompat.getDrawable(iv.context, R.drawable.ic_empty_start)
    }
}