package com.jay.data

import com.jay.data.local.GithubLocalDataSource
import com.jay.data.mapper.DataGithubMapper
import com.jay.data.remote.GithubRemoteDataSource
import com.jay.domain.model.DomainUser
import com.jay.domain.repository.GithubRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val remoteDataSource: GithubRemoteDataSource,
    private val localDataSource: GithubLocalDataSource
) : GithubRepository {

    override fun searchUser(searchName: String): Flowable<List<DomainUser>> {
        return remoteDataSource.searchUser(searchName)
            .flatMapPublisher {
                Single.just(it.map(DataGithubMapper::mapToDomain))
                    .toFlowable()
            }
    }

}