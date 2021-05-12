package com.example.delivery1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.delivery1.data.UserEntity
import com.example.delivery1.databinding.ActivityAddDriverBinding
import com.example.delivery1.databinding.ActivityProfileEditBinding

class ProfileEditActivity : AppCompatActivity() , ListAdapterDriverProfile.RowClickListener {

    lateinit var recyclerViewAdapter: ListAdapterDriverProfile
    lateinit var viewModel: UserViewModel
    private lateinit var bindingDrivers: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("Username")

        bindingDrivers = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(bindingDrivers.root)

        val recyclerView : RecyclerView = bindingDrivers.driversInfoListEdit
        recyclerViewAdapter = ListAdapterDriverProfile(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter
        val divider = DividerItemDecoration(this, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(divider)

        val role = resources.getStringArray(R.array.role)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item_delivery_status,role)
        bindingDrivers.roleInput.setAdapter(arrayAdapter)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            recyclerViewAdapter.setData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })


        bindingDrivers.saveButton.setOnClickListener {
            this.insertDataOrShowInfo()
        }
    }


    private fun insertDataOrShowInfo() {
        val role = bindingDrivers.roleInput.text.toString()
        val username  = bindingDrivers.nameInput.text.toString()
        val email  = bindingDrivers.emailInput.text.toString()
        val phone = bindingDrivers.phoneInput.text.toString()
        val password = bindingDrivers.passwordInput.text.toString()
        if (this.inputCheck(role, username, email, phone, password)) {
            if(bindingDrivers.saveButton.text.equals("Save")) {
                val user = UserEntity(0, role,username, password,email, phone,)
                viewModel.insertUserInfo(user)
                Toast.makeText(this,"Successfully Added!", Toast.LENGTH_SHORT).show()
            } else {
                val user = UserEntity(bindingDrivers.nameInput.getTag(bindingDrivers.nameInput.id).toString().toInt(),role ,username, password,email, phone)
                viewModel.updateUserInfo(user)
                bindingDrivers.saveButton.setText("Save")
            }
            bindingDrivers.roleInput.setText("")
            bindingDrivers.nameInput.setText("")
            bindingDrivers.emailInput.setText("")
            bindingDrivers.phoneInput.setText("")
            bindingDrivers.passwordInput.setText("")
            Toast.makeText(applicationContext,"Successfully Added!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext,"Please fill all fields!", Toast.LENGTH_SHORT).show()
        }

    }


    private fun inputCheck(A: String,
                           B: String,
                           C: String,
                           D: String,
                           E: String) : Boolean {
        return !(TextUtils.isEmpty(A) || TextUtils.isEmpty(B) || TextUtils.isEmpty(C) || TextUtils.isEmpty(D) || TextUtils.isEmpty(E))
    }

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
}