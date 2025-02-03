package com.actia.tunisialeague.models

import java.io.Serializable

data class Match(
    val id: Int,
    val team1: Team,
    val team2: Team,
    val date: String
): Serializable