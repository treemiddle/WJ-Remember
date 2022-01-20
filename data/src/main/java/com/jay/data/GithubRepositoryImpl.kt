package com.jay.data

import com.jay.data.mapper.DataGithubMapper
import com.jay.data.remote.GithubRemoteDataSource
import com.jay.domain.model.DomainUser
import com.jay.domain.repository.GithubRepository
import io.reactivex.Single

class GithubRepositoryImpl(
    private val remoteDataSource: GithubRemoteDataSource
) : GithubRepository {

    override fun searchUser(searchName: String): Single<List<DomainUser>> {
        return remoteDataSource.searchUser(searchName)
            .map { it.map(DataGithubMapper::mapToDomain) }
    }

}