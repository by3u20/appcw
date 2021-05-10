package com.example.delivery1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM DeliveriesTable")
    fun getDeliveries(): LiveData<List<Delivery>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDelivery(pkg: Delivery)

    @Update
    fun updDelivery(pkg: Delivery)

    @Delete
    fun delDelivery(pkg: Delivery)
}