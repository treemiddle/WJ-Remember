package com.jay.remote.model

data class GithubReponse<T>(
    val incomplete_results: Boolean,
    val items: List<T>?,
    val total_count: Int
)