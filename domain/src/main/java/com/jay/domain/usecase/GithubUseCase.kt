package com.jay.domain.usecase

import com.jay.domain.model.DomainUser
import com.jay.domain.repository.GithubRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GithubUseCase @Inject constructor(private val githubRepository: GithubRepository) {

    fun searchUser(searchName: String): Flowable<List<DomainUser>> {
        return githubRepository.searchUser(searchName)
    }

}