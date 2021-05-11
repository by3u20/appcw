package com.example.delivery1.data

import androidx.lifecycle.LiveData

class DeliveryRepository (private val deliveryDao: DeliveryDao, who: String){
    val getDeliveries : LiveData<List<Delivery>> = if (who == "Admin") deliveryDao.getDeliveries() else deliveryDao.getDeliveriesWithDriverName(who)

    suspend fun addDelivery(delivery: Delivery){
        deliveryDao.addDelivery(delivery)
    }

    suspend fun updDelivery(delivery: Delivery){
        deliveryDao.updDelivery(delivery)
    }
}