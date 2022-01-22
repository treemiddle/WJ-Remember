package com.jay.domain.model

data class DomainUser(
    val id: Long,
    val name: String,
    val image: String?,
    val hasLiked: Boolean = false,
    val header: String? = null
) : DomainModel