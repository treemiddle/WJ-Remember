package com.jay.domain.repository

import com.jay.domain.model.DomainUser
import io.reactivex.Flowable

interface GithubRepository {

    fun searchUser(searchName: String): Flowable<List<DomainUser>>

}