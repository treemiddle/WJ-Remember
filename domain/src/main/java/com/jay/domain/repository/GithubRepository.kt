package com.jay.domain.repository

import com.jay.domain.model.DomainUser
import io.reactivex.Single

interface GithubRepository {

    fun searchApiUsers(name: String): Single<List<DomainUser>>

    fun searchLocalUsers(name: String): Single<List<DomainUser>>

}