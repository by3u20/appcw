package com.example.delivery1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.delivery1.R
import com.example.delivery1.databinding.FragmentAddDeliveriesBinding
import com.google.android.material.textfield.TextInputLayout


class AddDeliveries : Fragment() {
    private  var _binding : FragmentAddDeliveriesBinding? = null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        _binding = FragmentAddDeliveriesBinding.inflate(inflater, container, false)

        /*------------------------Add Delivereies---------------------------------*/
        val deliveryStatus = resources.getStringArray(R.array.delivery_status)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item_delivery_status,deliveryStatus)

        binding.addDeliveriesStatusText.setAdapter(arrayAdapter)

        return binding.root
    }

}