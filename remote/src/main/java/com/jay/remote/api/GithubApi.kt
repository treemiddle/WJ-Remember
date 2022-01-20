package com.jay.remote.api

import com.jay.remote.model.GithubReponse
import com.jay.remote.model.UserInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    fun searchUser(
        @Query("q") searchName: String,
        @Query("per_page") per_page: Int = 100,
        @Query("order") sort: String = "asc"
    ): Single<GithubReponse<UserInfo>>

}