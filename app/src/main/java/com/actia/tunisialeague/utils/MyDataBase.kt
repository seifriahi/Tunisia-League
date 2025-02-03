package com.actia.tunisialeague.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.actia.tunisialeague.dao.*
import com.actia.tunisialeague.models.*

@Database(entities = [User::class, Team::class], version = 1, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun teamDao(): TeamDao

    companion object {
        private var instance: MyDataBase? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDataBase {
            if (instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext,
                    MyDataBase::class.java,"tn_league_db")
                    .allowMainThreadQueries()
                    .build()

            return instance!!
        }
    }
}