package com.jay.data

import com.jay.data.local.GithubLocalDataSource
import com.jay.data.mapper.DataGithubMapper
import com.jay.data.model.DataUser
import com.jay.data.remote.GithubRemoteDataSource
import com.jay.domain.model.DomainUser
import com.jay.domain.repository.GithubRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val remoteDataSource: GithubRemoteDataSource,
    private val localDataSource: GithubLocalDataSource
) : GithubRepository {

    override fun searchApiUsers(name: String): Single<List<DomainUser>> {
        return remoteDataSource.searchUser(name)
            .flatMap { apiUsers ->
                Observable.fromIterable(apiUsers)
                    .concatMapEagerDelayError({ user ->
                        Observable.just(user.id)
                            .flatMap { id ->
                                localDataSource.getUserLike(id)
                                    .toObservable()
                                    .map(DataUser::hasLiked)
                                    .onErrorReturnItem(false)
                                    .map { hasLiked -> user.copy(hasLiked = hasLiked) }
                            }
                    }, true)
                    .toList()
                    .map { it.map(DataGithubMapper::mapToDomain) }
            }
    }

    override fun searchLocalUsers(name: String): Single<List<DomainUser>> {
        return localDataSource.getUserFromName(name)
            .map { it.map(DataGithubMapper::mapToDomain) }
    }

    override fun saveUser(user: DomainUser): Completable {
        return localDataSource.saveUserLike(DataGithubMapper.mapToData(user))
    }

    override fun deleteUser(user: DomainUser): Completable {
        return localDataSource.deleteUserLike(DataGithubMapper.mapToData(user))
    }



}