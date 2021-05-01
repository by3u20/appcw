package moe.yhi.apps.playground.data

import androidx.room.*
import androidx.room.Dao

@Dao
interface DeliveryDao {
    @Query("SELECT * FROM Deliveries")
    suspend fun getDeliveries(): Array<Delivery>

    @Query("SELECT * FROM Deliveries WHERE status = :status")
    suspend fun getDeliveriesWithStatus(status: DeliveryStatus): Array<Delivery>

    @Transaction
    @Query("SELECT * FROM Deliveries")
    suspend fun getDeliveriesFromToDriver(): Array<DeliveryFromToDriver>

    @Insert
    suspend fun addDelivery(pkg: Delivery)

    @Update
    suspend fun updDelivery(pkg: Delivery)

    @Delete
    suspend fun delDelivery(pkg: Delivery)
}