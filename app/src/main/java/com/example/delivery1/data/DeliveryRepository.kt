package com.example.delivery1.data

import androidx.lifecycle.LiveData

class DeliveryRepository (private  val deliveryDao: DeliveryDao){
    val getDeliveries : LiveData<List<Delivery>> = deliveryDao.getDeliveries()

    suspend fun addDelivery(delivery: Delivery){
        deliveryDao.addDelivery(delivery)
    }

    suspend fun updDelivery(delivery: Delivery){
        deliveryDao.updDelivery(delivery)
    }
}