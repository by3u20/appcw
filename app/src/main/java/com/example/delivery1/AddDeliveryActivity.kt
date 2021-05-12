package com.example.delivery1

import android.os.Bundle
import android.os.StrictMode
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.delivery1.data.Delivery
import com.example.delivery1.data.DeliveryViewModel
import com.example.delivery1.data.LocalDatabase.Companion.getDatabase
import com.example.delivery1.databinding.ActivityAddDeliveryBinding

class AddDeliveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDeliveryBinding
    private lateinit var mDeliveryViewModel: DeliveryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val deliveryStatus = resources.getStringArray(R.array.delivery_status)
        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.dropdown_item_delivery_status,
            deliveryStatus
        )
        binding.addDeliveriesStatusText.setAdapter(arrayAdapter)


        mDeliveryViewModel = ViewModelProvider(this).get(DeliveryViewModel::class.java)

        binding.addDeliveryButtonWithInfo.setOnClickListener {
            this.insertDataToDatabase()
        }
    }

    private fun inputCheck(
        deliveriesID: String,
        deliveryStatus: String,
        fromID: String,
        toID: String,
        driverID: String
    ) : Boolean {
        return !(TextUtils.isEmpty(deliveriesID) || TextUtils.isEmpty(deliveryStatus) || TextUtils.isEmpty(
            fromID
        ) || TextUtils.isEmpty(toID) || TextUtils.isEmpty(driverID))
    }

    private fun insertDataToDatabase() {
        val deliveriesID = binding.addDeliveriesIdText.text.toString()
        val deliveryStatus = binding.addDeliveriesStatusText.text.toString()
        val fromID = binding.addDeliveriesFromIdText.text.toString()
        val toID = binding.addDeliveriesToIdText.text.toString()
        val driverID = binding.addDeliveriesDriverIdText.text.toString()

        if (this.inputCheck(deliveriesID, deliveryStatus, fromID, toID, driverID)) {
            val users = getDatabase(applicationContext).userDao().getAllUserInfo()
            for (user in users) {
                if (driverID == user.username){
                    val delivery = Delivery(deliveriesID, deliveryStatus, fromID, toID, driverID)
                    mDeliveryViewModel.addDelivery(delivery)
                    Toast.makeText(applicationContext, "Successfully Added!", Toast.LENGTH_SHORT).show()
                    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                    StrictMode.setThreadPolicy(policy)
                    val a : Sendemail = Sendemail()
                    a.Sendemail(user.email)
                }
                else{
                    Toast.makeText(applicationContext, "Don't have this driver", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(applicationContext, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
    }

}
