package com.actia.tunisialeague.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.actia.tunisialeague.models.User

@Dao
interface UserDao {

    @Insert
    fun insert(u: User)

    @Update
    fun update(u: User)

    @Delete
    fun delete(u: User)

    @Query("SELECT * FROM userTable")
    fun getAllUsers() : MutableList<User>

    @Query("SELECT * FROM userTable WHERE email like :email AND password like :pwd LIMIT 1")
    fun getUserByEmailAndPassword(email: String, pwd: String) : User
}