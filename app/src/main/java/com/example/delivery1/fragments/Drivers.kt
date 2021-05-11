package com.example.delivery1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery1.*
import com.example.delivery1.data.DeliveryViewModel
import com.example.delivery1.data.LocalDatabase


class Drivers : Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drivers, container, false)

        val adapter = ListAdapterDriver()
        val recyclerView: RecyclerView = view.findViewById(R.id.drivers_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        LocalDatabase.getDatabase(requireContext())
            .userDao()
            .getAllUserInfo()
            .observe(viewLifecycleOwner, Observer {
                adapter.setData(it)
            })

        view.findViewById<Button>(R.id.add_new_driver_button).setOnClickListener {
            startActivity(Intent(activity, AddDriverActivity::class.java))
        }

        return view
    }

}