package com.example.delivery1.data

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM Users")
    fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Update
    fun updUser(user: User)

    @Delete
    fun delUser(user: User)
}