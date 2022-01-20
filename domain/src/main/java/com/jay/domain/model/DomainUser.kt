package com.jay.domain.model

data class DomainUser(
    val name: String,
    val image: String?,
    val hasLiked: Boolean = false,
    val header: Boolean = false
) : DomainModel