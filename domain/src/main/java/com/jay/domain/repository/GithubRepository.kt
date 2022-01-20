package com.jay.domain.repository

import com.jay.domain.model.DomainUser
import io.reactivex.Single

interface GithubRepository {

    fun searchUser(searchName: String): Single<List<DomainUser>>

}