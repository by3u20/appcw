package moe.yhi.apps.playground.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.*

@Entity(
    tableName = "Deliveries",
    primaryKeys = ["id"],
    foreignKeys = [
        ForeignKey(entity = Site::class, parentColumns = ["id"], childColumns = ["from_id"], onDelete = SET_DEFAULT, onUpdate = CASCADE),
        ForeignKey(entity = Site::class, parentColumns = ["id"], childColumns = ["to_id"], onDelete = SET_DEFAULT, onUpdate = CASCADE),
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["driver_id"], onDelete = SET_DEFAULT, onUpdate = CASCADE),
    ],
)
data class Delivery(
    val id: Int,
    val status: DeliveryStatus,
    val from_id: Int,
    val to_id: Int,
    val driver_id: Int,
)