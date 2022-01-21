package com.jay.domain.usecase

import com.jay.domain.model.DomainUser
import com.jay.domain.repository.GithubRepository
import io.reactivex.Completable
import javax.inject.Inject

class LocalUseCase @Inject constructor(private val githubRepository: GithubRepository) {

    fun saveUserLike(user: DomainUser): Completable {
        return githubRepository.saveUser(user)
    }

    fun deleteUserLike(user: DomainUser): Completable {
        return githubRepository.deleteUser(user)
    }

}