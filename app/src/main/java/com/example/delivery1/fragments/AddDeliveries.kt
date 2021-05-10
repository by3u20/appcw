package com.example.delivery1.fragments

import android.content.ClipData
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
//import com.example.delivery1.data
import com.example.delivery1.R
import com.example.delivery1.data.Delivery
import com.example.delivery1.data.DeliveryViewModel
import com.example.delivery1.databinding.FragmentAddDeliveriesBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView


class AddDeliveries : Fragment() {

    private var _binding : FragmentAddDeliveriesBinding? = null
    private val binding get()= _binding!!
    private lateinit var mDeliveryViewModel: DeliveryViewModel

    override fun onResume() {
        super.onResume()
        val deliveryStatus =resources.getStringArray(R.array.delivery_status)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item_delivery_status,deliveryStatus)
        //binding.addDeliveriesStatusText.setAdapter(arrayAdapter )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddDeliveriesBinding.inflate(inflater, container, false)
        mDeliveryViewModel = ViewModelProvider(this).get(DeliveryViewModel::class.java)

        binding.addDeliveryButtonWithInfo.setOnClickListener {
            //insertDataToDatabase()
            Toast.makeText(requireContext(),"Clicked!",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val deliveriesID = binding.addDeliveriesIdText.text.toString()
        val deliveryStatus = binding.addDeliveriesStatusText.text.toString()
        val fromID = binding.addDeliveriesFromIdText.text.toString()
        val toID = binding.addDeliveriesToIdText.text.toString()
        val driverID = binding.addDeliveriesDriverIdText.text.toString()
        if(inputCheck(deliveriesID,deliveryStatus,fromID,toID,driverID)){
            val delivery = Delivery(deliveriesID,deliveryStatus,fromID,toID,driverID,null,null,null,null)
            mDeliveryViewModel.addDelivery(delivery)
            Toast.makeText(requireContext(),"Successfully Added!",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(requireContext(),"Please fill all fields!",Toast.LENGTH_SHORT).show()
        }
    }


    private fun inputCheck(deliveriesID: String,deliveryStatus: String,fromID: String,toID: String,driverID: String) : Boolean{
        return !(TextUtils.isEmpty(deliveriesID) && TextUtils.isEmpty(deliveryStatus) && TextUtils.isEmpty(fromID) && TextUtils.isEmpty(toID) && TextUtils.isEmpty(driverID))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
