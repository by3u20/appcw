package com.example.delivery1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery1.ListAdapterDelivery
import com.example.delivery1.R
import com.example.delivery1.data.DeliveryViewModel

class Drivers : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_drivers, container, false)

        val adapter = ListAdapterDelivery()
        var mDeliveryViewModel: DeliveryViewModel
        val recyclerView: RecyclerView = view.findViewById(R.id.drivers_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mDeliveryViewModel = ViewModelProvider(this).get(DeliveryViewModel::class.java)
        mDeliveryViewModel.getDeliveries.observe(viewLifecycleOwner, Observer { delivery ->
            adapter.setData(delivery)
        })

        return view
    }

}