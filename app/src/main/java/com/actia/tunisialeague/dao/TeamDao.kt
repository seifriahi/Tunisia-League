package com.actia.tunisialeague.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.actia.tunisialeague.models.Team

@Dao
interface TeamDao {

    @Insert
    fun insert(t: Team)

    @Update
    fun update(t: Team)

    @Delete
    fun delete(t: Team)

    @Query("SELECT * FROM teamTable")
    fun getAllTeams() : MutableList<Team>

    @Query("SELECT * FROM teamTable WHERE id == :id LIMIT 1")
    fun getTeamById(id: Int) : Team

}