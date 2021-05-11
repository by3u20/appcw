package com.example.delivery1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.delivery1.data.UserRecylerViewAdapter
import com.example.delivery1.data.UserViewModel
import com.example.delivery1.R
import com.example.delivery1.data.UserEntity
import com.example.delivery1.databinding.FragmentDriversBinding

class Drivers : Fragment() ,UserRecylerViewAdapter.RowClickListener{


    lateinit var recyclerViewAdapter: UserRecylerViewAdapter
    lateinit var viewModel: UserViewModel
    private lateinit var bindingDrivers: FragmentDriversBinding




    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UserEntity) {
        bindingDrivers.nameInput.setText(user.username)
        bindingDrivers.emailInput.setText(user.email)
        bindingDrivers.phoneInput.setText(user.phone)
        bindingDrivers.nameInput.setTag(bindingDrivers.nameInput.id, user.id)
        bindingDrivers.saveButton.setText("Update")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_drivers, container, false)

        bindingDrivers = FragmentDriversBinding.inflate(layoutInflater)

        recyclerViewAdapter = UserRecylerViewAdapter(this@Drivers)
        bindingDrivers.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        bindingDrivers.recyclerView.adapter = recyclerViewAdapter

        bindingDrivers.recyclerView.apply {
            val divider = DividerItemDecoration(context, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getAllUsersObservers().observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })


        bindingDrivers.saveButton.setOnClickListener {
            val name  = bindingDrivers.nameInput.text.toString()
            val email  = bindingDrivers.emailInput.text.toString()
            val phone = bindingDrivers.phoneInput.text.toString()
            if(bindingDrivers.saveButton.text.equals("Save")) {
                val user = UserEntity(0, "Driver",name, "123456",email, phone,)
                viewModel.insertUserInfo(user)
            } else {
                val user = UserEntity(bindingDrivers.nameInput.getTag(bindingDrivers.nameInput.id).toString().toInt(),"Driver" ,name, "123456",email, phone)
                viewModel.updateUserInfo(user)
                bindingDrivers.saveButton.setText("Save")
            }
            bindingDrivers.nameInput.setText("")
            bindingDrivers.emailInput.setText("")
        }
        return view
    }

}