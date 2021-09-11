package com.xuanlocle.usermanager.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xuanlocle.usermanager.data.source.local.dao.UserDao
import com.xuanlocle.usermanager.data.source.local.entity.DBUserEntity

@Database(entities = [DBUserEntity::class], version = 1, exportSchema = true)
abstract class UserDatabase() : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {

        @Volatile
        private var instance: UserDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java, "repo.db"
            )
                .build()


    }

}