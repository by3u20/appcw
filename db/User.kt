package moe.yhi.apps.playground.data

import androidx.room.Entity

@Entity(
    tableName = "Users",
    primaryKeys = ["id"],
)
data class User(
    val id: Int,
    val role: UserRole,
    val user_id: String,
    val username: String,
    val password: String,
    val association: String?,
    val phone: String?,
)