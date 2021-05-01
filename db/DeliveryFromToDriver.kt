package moe.yhi.apps.playground.data

import androidx.room.Embedded
import androidx.room.Relation

data class DeliveryFromToDriver(
    @Embedded
    val pkg: Delivery,
    @Relation(parentColumn = "from_id", entityColumn = "id")
    val from: Site,
    @Relation(parentColumn = "to_id", entityColumn = "id")
    val to: Site,
    @Relation(parentColumn = "driver_id", entityColumn = "id")
    val driver: User,
)