package com.jay.remote

import com.jay.data.model.DataUser
import com.jay.data.remote.GithubRemoteDataSource
import com.jay.remote.api.GithubApi
import com.jay.remote.mapper.GithubRemoteMapper
import io.reactivex.Single

class GithubRemoteDataSourceImpl(private val githubApi: GithubApi) : GithubRemoteDataSource {

    override fun searchUser(searchName: String): Single<List<DataUser>> {
        return githubApi.searchUser(searchName)
            .map { it.items?.map(GithubRemoteMapper::mapToData) }
    }

}