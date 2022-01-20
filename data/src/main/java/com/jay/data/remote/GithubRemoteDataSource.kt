package com.jay.data.remote

import com.jay.data.model.DataUser
import io.reactivex.Single

interface GithubRemoteDataSource {

    fun searchUser(searchName: String): Single<List<DataUser>>

}