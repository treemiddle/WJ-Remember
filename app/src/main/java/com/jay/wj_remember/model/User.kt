package com.jay.wj_remember.model

data class User(
    val name: String,
    val image: String?,
    var hasLiked: Boolean = false
) : PresentationModel