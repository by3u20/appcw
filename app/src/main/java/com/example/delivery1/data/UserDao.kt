package com.example.delivery1.data

import androidx.room.*
import androidx.room.Dao

@Dao
interface UserDao {
    @Query("SELECT * FROM Users")
    suspend fun getUsers(): Array<User>

    @Query("SELECT * FROM Users WHERE role = :role")
    suspend fun getUsersWithRole(role: UserRole): Array<User>

    @Query("SELECT * FROM Users WHERE user_id = :uid AND password = :password LIMIT 1")
    suspend fun getUserWithUidPassword(uid: String, password: String): User?

    @Insert
    suspend fun addUser(user: User)

    @Update
    suspend fun updUser(user: User)

    @Delete
    suspend fun delUser(user: User)
}