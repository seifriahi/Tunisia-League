package com.actia.tunisialeague.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class User(
  @PrimaryKey(autoGenerate = true) var id: Int = 0,
  var firstName: String,
  var lastName: String,
  var email: String,
  var password: String
)