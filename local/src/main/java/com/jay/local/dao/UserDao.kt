package com.jay.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.jay.local.model.LocalEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id =:id")
    fun getUserLike(id: Long): Single<LocalEntity>

    @Insert(onConflict = REPLACE)
    fun saveUserLike(user: LocalEntity): Completable

    @Delete
    fun deleteUserLike(user: LocalEntity): Completable

    @Query("SELECT * FROM user WHERE name LIKE '%' || :name || '%' ORDER BY name ASC")
    fun getUserFromName(name: String): Single<List<LocalEntity>>

}