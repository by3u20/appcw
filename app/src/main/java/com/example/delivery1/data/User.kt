package com.example.delivery1.data

import androidx.room.Entity

@Entity(
    tableName = "Users",
    primaryKeys = ["id"]
)
data class User(
    val id: String,
    val role: String,
    val password: String,
)
