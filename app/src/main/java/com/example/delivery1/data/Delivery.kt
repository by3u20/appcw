package com.example.delivery1.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.*

@Entity(
    tableName = "DeliveriesTable",
    primaryKeys = ["id"],
)
data class Delivery(
    val id: String,
    val status: String,
    val from_id: String,
    val to_id: String,
    val driver_id: String,
    val role: String,
    val username: String,
    val password: String,
    val phone: String?,
)