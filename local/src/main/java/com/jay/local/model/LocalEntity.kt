package com.jay.local.model

data class LocalEntity(
    val name: String,
    val image: String?,
    val hasLiked: Boolean
) : LocalModel