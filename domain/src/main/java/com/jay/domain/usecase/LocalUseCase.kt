package com.jay.domain.usecase

import com.jay.domain.model.DomainUser
import com.jay.domain.repository.LocalRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Data Layer에 있는 LocalRepositoryImpl에 데이터 요청
 */
class LocalUseCase @Inject constructor(private val localRepository: LocalRepository) {

    fun saveUserLike(user: DomainUser): Completable {
        return localRepository.saveUser(user)
    }

    fun deleteUserLike(user: DomainUser): Completable {
        return localRepository.deleteUser(user)
    }

}