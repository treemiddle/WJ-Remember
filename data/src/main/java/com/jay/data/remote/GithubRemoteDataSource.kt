package com.jay.data.remote

import com.jay.data.model.DataUser
import io.reactivex.Single

interface GithubRemoteDataSource {

    fun searchUser(name: String): Single<List<DataUser>>

}