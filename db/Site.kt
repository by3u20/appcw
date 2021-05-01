package moe.yhi.apps.playground.data

import androidx.room.Entity

@Entity(
    tableName = "Sites",
    primaryKeys = ["id"],
)
data class Site(
    val id: Int,
    val role: SiteRole,
    val name: String,
    val location: String,
)