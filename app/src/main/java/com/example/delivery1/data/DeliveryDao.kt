package com.example.delivery1.data

import androidx.room.*
import androidx.room.Dao

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM Deliveries")
    fun getDeliveries(): Array<Delivery>

    @Query("SELECT * FROM Deliveries WHERE status = :status")
    fun getDeliveriesWithStatus(status: DeliveryStatus): Array<Delivery>

    @Transaction
    @Query("SELECT * FROM Deliveries")
    fun getDeliveriesFromToDriver(): Array<DeliveryFromToDriver>

    @Insert
    fun addDelivery(pkg: Delivery)

    @Update
    fun updDelivery(pkg: Delivery)

    @Delete
    fun delDelivery(pkg: Delivery)
}