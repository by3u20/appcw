package com.example.delivery1.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM DeliveriesTable")
    fun getDeliveries(): LiveData<List<Delivery>>

    @Query("SELECT * FROM DeliveriesTable WHERE status = :status")
    fun getDeliveriesWithStatus(status: DeliveryStatus): Array<Delivery>

    @Transaction
    @Query("SELECT * FROM DeliveriesTable")
    fun getDeliveriesFromToDriver(): Array<DeliveryFromToDriver>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDelivery(pkg: Delivery)

    @Update
    fun updDelivery(pkg: Delivery)

    @Delete
    fun delDelivery(pkg: Delivery)
}