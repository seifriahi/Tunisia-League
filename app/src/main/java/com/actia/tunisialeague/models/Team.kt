package com.actia.tunisialeague.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "teamTable")
data class Team(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val rank: Int,
    @DrawableRes
    val image: Int,
    val name: String,
    val playedMatch: Int,
    val victory: Int,
    val draws: Int,
    val defeat: Int
) : Serializable