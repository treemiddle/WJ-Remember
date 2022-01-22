package com.jay.data.local

import com.jay.data.model.DataUser
import io.reactivex.Completable
import io.reactivex.Single

interface UserLocalDataSource {

    fun getUserLike(id: Long): Single<DataUser>

    fun saveUserLike(user: DataUser): Completable

    fun deleteUserLike(user: DataUser): Completable

    fun getUserFromName(name: String): Single<List<DataUser>>

}