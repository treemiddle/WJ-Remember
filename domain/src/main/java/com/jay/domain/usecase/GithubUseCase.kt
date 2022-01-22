package com.jay.domain.usecase

import com.jay.domain.model.DomainUser
import com.jay.domain.repository.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Data Layer에 있는 GithubRepositoryImpl에 데이터 요청
 */
class GithubUseCase @Inject constructor(private val githubRepository: GithubRepository) {

    fun searchApiUsers(name: String): Single<List<DomainUser>> {
        return githubRepository.searchApiUsers(name)
    }

    fun searchLocalUsers(name: String): Single<List<DomainUser>> {
        return githubRepository.searchLocalUsers(name)
    }

}