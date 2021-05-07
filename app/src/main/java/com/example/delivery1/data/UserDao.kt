package com.example.delivery1.data

import androidx.room.*
import androidx.room.Dao

@Dao
interface UserDao {
    @Query("SELECT * FROM Users")
    fun getUsers(): Array<User>

    @Query("SELECT * FROM Users WHERE role = :role")
    fun getUsersWithRole(role: UserRole): Array<User>

    @Query("SELECT * FROM Users WHERE user_id = :uid AND password = :password LIMIT 1")
    fun getUserWithUidPassword(uid: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    fun updUser(user: User)

    @Delete
    fun delUser(user: User)
}