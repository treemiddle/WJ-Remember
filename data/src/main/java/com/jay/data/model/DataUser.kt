package com.jay.data.model

data class DataUser(
    val id: Long,
    val name: String,
    val image: String?,
    var hasLiked: Boolean = false,
    var header: String? = null
) : DataModel