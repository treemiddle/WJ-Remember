package com.jay.local

import com.jay.data.local.GithubLocalDataSource
import com.jay.data.model.DataUser
import com.jay.local.dao.UserDao
import com.jay.local.mapper.GithubLocalMapper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class GithubLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : GithubLocalDataSource {

    override fun getUserLike(id: Long): Single<DataUser> {
        return userDao.getUserLike(id)
            .map(GithubLocalMapper::mapToData)
    }

    override fun saveUserLike(user: DataUser): Completable {
        return userDao.saveUserLike(GithubLocalMapper.mapToLocal(user))
    }

    override fun deleteUserLike(user: DataUser): Completable {
        return userDao.deleteUserLike(GithubLocalMapper.mapToLocal(user))
    }

    override fun getUserFromName(name: String): Single<List<DataUser>> {
        return userDao.getUserFromName(name)
            .map { it.map(GithubLocalMapper::mapToData) }
    }

}