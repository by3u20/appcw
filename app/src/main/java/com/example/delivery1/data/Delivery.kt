package com.example.delivery1.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.*

@Entity(
    tableName = "DeliveriesTable",
    primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(entity = Site::class, parentColumns = ["id"], childColumns = ["from_id"], onDelete = SET_DEFAULT, onUpdate = CASCADE),
        ForeignKey(entity = Site::class, parentColumns = ["id"], childColumns = ["to_id"], onDelete = SET_DEFAULT, onUpdate = CASCADE),
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["driver_id"], onDelete = SET_DEFAULT, onUpdate = CASCADE),
    ],
)
data class Delivery(
    val id: String,
    val status: String,
    val from_id: String,
    val to_id: String,
    val driver_id: String,
)