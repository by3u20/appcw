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
import com.example.delivery1.ListAdapterVendor
import com.example.delivery1.R
import com.example.delivery1.data.DeliveryViewModel

class Vendors : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vendors, container, false)

        val adapter = ListAdapterVendor()
        val mDeliveryViewModel = ViewModelProvider(this).get(DeliveryViewModel::class.java)
        val recyclerView: RecyclerView = view.findViewById(R.id.vendors_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mDeliveryViewModel.getDeliveries.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        return view
    }

}