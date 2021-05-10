package com.example.delivery1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.delivery1.databinding.FragmentAddDeliveriesBinding

class AddDeliveryActivity : AppCompatActivity() {

    private lateinit var binding: FragmentAddDeliveriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_delivery)
    }

}