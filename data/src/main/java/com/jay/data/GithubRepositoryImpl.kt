package com.jay.data

import com.jay.data.local.UserLocalDataSource
import com.jay.data.mapper.DataGithubMapper
import com.jay.data.model.DataUser
import com.jay.data.remote.GithubRemoteDataSource
import com.jay.domain.model.DomainUser
import com.jay.domain.repository.GithubRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Remote, Local에서 값을 가져와서 조합해 Domain Layer로 내려줌
 */
class GithubRepositoryImpl @Inject constructor(
    private val githubRemoteDataSource: GithubRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : GithubRepository {

    override fun searchApiUsers(name: String): Single<List<DomainUser>> {
        return githubRemoteDataSource.searchUser(name)
            .flatMap { apiUsers ->
                Observable.fromIterable(apiUsers)
                    .concatMapEagerDelayError({ user ->
                        Observable.just(user.id)
                            .flatMap { id ->
                                userLocalDataSource.getUserLike(id)
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
        return userLocalDataSource.getUserFromName(name)
            .map { it.map(DataGithubMapper::mapToDomain) }
    }

}