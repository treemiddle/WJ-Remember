package com.jay.wj_remember.utils.ext

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jay.wj_remember.R

fun ImageView.loadImage(path: String?, circularProgress: CircularProgressDrawable) {
    Glide.with(context)
        .load(path)
        .circleCrop()
        .apply(
            RequestOptions()
                .placeholder(circularProgress)
                .error(R.drawable.ic_error))
        .into(this)
}