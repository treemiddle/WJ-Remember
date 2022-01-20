package com.jay.domain.usecase

import com.jay.domain.model.DomainUser
import com.jay.domain.repository.GithubRepository
import io.reactivex.Single

class GithubUseCase(private val githubRepository: GithubRepository) {

    fun searchUser(searchName: String): Single<List<DomainUser>> {
        return githubRepository.searchUser(searchName)
    }

}