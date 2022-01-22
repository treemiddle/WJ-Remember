package com.jay.domain.repository

import com.jay.domain.model.DomainUser
import io.reactivex.Completable

interface LocalRepository {

    fun saveUser(user: DomainUser): Completable

    fun deleteUser(user: DomainUser): Completable

}