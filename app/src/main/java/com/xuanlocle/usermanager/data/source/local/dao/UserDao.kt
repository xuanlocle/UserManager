package com.xuanlocle.usermanager.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xuanlocle.usermanager.data.source.local.entity.DBUserEntity
import com.xuanlocle.usermanager.data.source.local.entity.DBUserProfileEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(dbUserLists: List<DBUserEntity>)

    @Query("SELECT * FROM user_table WHERE id > :since ORDER BY id")
    suspend fun getUserSinceId(since: Int): List<DBUserEntity>

    @Query("SELECT count(id) FROM user_table WHERE id > :sinceUserId LIMIT 1")
    suspend fun checkUsersExists(sinceUserId: Int): Int

    @Query("DELETE FROM user_table")
    suspend fun deleteAllRepo()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(dbUser: DBUserProfileEntity)

    @Query("SELECT * FROM user_profile_table WHERE login = :userLoginId LIMIT 1")
    suspend fun getUserProfileById(userLoginId: String): DBUserProfileEntity

    @Query("SELECT count(id) FROM user_profile_table WHERE login = :userLoginId LIMIT 1")
    suspend fun checkUserProfileExists(userLoginId: String): Int

}
