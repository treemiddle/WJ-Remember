package com.jay.wj_remember.model

data class User(
    val name: String,
    val image: String?,
    val header: Boolean,
    var hasLiked: Boolean = false,
) : PresentationModel