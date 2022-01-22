package com.jay.data

import com.jay.data.local.UserLocalDataSource
import com.jay.data.mapper.DataGithubMapper
import com.jay.domain.model.DomainUser
import com.jay.domain.repository.LocalRepository
import io.reactivex.Completable
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource
) : LocalRepository {

    override fun saveUser(user: DomainUser): Completable {
        return localDataSource.saveUserLike(DataGithubMapper.mapToData(user))
    }

    override fun deleteUser(user: DomainUser): Completable {
        return localDataSource.deleteUserLike(DataGithubMapper.mapToData(user))
    }

}