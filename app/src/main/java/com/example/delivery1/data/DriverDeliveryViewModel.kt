package com.example.delivery1.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DriverDeliveryViewModel(application: Application) : AndroidViewModel(application){
    val getDeliveries : LiveData<List<Delivery>>
    val repository : DeliveryRepository

    init {
        val deliveryDao = LocalDatabase.getDatabase(application).deliveryDao()
        repository = DeliveryRepository(deliveryDao, "Sam") // XXX: Hardcode
        getDeliveries = repository.getDeliveries
    }

    fun addDelivery(delivery: Delivery){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDelivery(delivery)
        }
    }

    fun findVendors(delivery: Delivery){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDelivery(delivery)
        }
    }

}


