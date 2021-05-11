package com.example.delivery1.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.delivery1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.delivery1.AddDeliveryActivity
import com.example.delivery1.data.DeliveryViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delivery1.ListAdapterDelivery

class Deliveries : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_deliveries, container, false)
        val addbutton: FloatingActionButton = view.findViewById(R.id.add_delivery_button)
        addbutton.setOnClickListener {
            val intent = Intent(activity, AddDeliveryActivity::class.java)
            startActivity(intent)
        }

        val adapter = ListAdapterDelivery()
        var mDeliveryViewModel: DeliveryViewModel
        val recyclerView: RecyclerView = view.findViewById(R.id.deliveries_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mDeliveryViewModel = ViewModelProvider(this).get(DeliveryViewModel::class.java)
        mDeliveryViewModel.getDeliveries.observe(viewLifecycleOwner, Observer { delivery ->
            adapter.setData(delivery)
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

}