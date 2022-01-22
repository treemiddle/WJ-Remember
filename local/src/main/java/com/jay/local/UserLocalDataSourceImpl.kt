package com.jay.local

import com.jay.data.local.UserLocalDataSource
import com.jay.data.model.DataUser
import com.jay.local.dao.UserDao
import com.jay.local.mapper.UserLocalMapper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Local에서 받아온 결과 값을 Data Layer로 매핑하여 내려줌
 */
class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserLocalDataSource {

    override fun getUserLike(id: Long): Single<DataUser> {
        return userDao.getUserLike(id)
            .map(UserLocalMapper::mapToData)
    }

    override fun saveUserLike(user: DataUser): Completable {
        return userDao.saveUserLike(UserLocalMapper.mapToLocal(user))
    }

    override fun deleteUserLike(user: DataUser): Completable {
        return userDao.deleteUserLike(UserLocalMapper.mapToLocal(user))
    }

    override fun getUserFromName(name: String): Single<List<DataUser>> {
        return userDao.getUserFromName(name)
            .map { it.map(UserLocalMapper::mapToData) }
            .map { setHeader(it) }
    }

    private fun setHeader(list: List<DataUser>): List<DataUser> {
        val newList = mutableListOf<DataUser>()
        val groupList = list.groupBy { it.name[0] }

        for ((key, valueList) in groupList) {
            valueList[0].header = key.toString()
            valueList.forEach { newList.add(it) }
        }

        return newList
    }

}