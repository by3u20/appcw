package com.example.delivery1.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.*

@Entity(
    tableName = "Deliveries",
    primaryKeys = ["id"],
)
data class Delivery(
    val id: String,
    val status: String,
    val from: String,
    val to: String,
    val driver: String,
)