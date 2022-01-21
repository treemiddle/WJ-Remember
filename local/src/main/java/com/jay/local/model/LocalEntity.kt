package com.jay.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class LocalEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val image: String?,
    val hasLiked: Boolean
) : LocalModel