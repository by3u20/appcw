package com.example.delivery1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.delivery1.data.LocalDatabase
import com.example.delivery1.data.UserEntity

class UserViewModel(app: Application): AndroidViewModel(app) {
    private var allUsers : MutableLiveData<List<UserEntity>> = MutableLiveData()

    init {
        getAllUsers()
    }

    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }

    fun getAllUsers() {
        val userDao = LocalDatabase.getDatabase((getApplication())).userDao()
        val list = userDao.getAllUserInfo()

        allUsers.postValue(list.value)
    }

    fun insertUserInfo(entity: UserEntity){
        val userDao = LocalDatabase.getDatabase(getApplication()).userDao()
        userDao.insertUser(entity)
        getAllUsers()
    }

    fun updateUserInfo(entity: UserEntity){
        val userDao = LocalDatabase.getDatabase(getApplication()).userDao()
        userDao.updateUser(entity)
        getAllUsers()
    }

    fun deleteUserInfo(entity: UserEntity){
        val userDao = LocalDatabase.getDatabase(getApplication()).userDao()
        userDao.deleteUser(entity)
        getAllUsers()
    }
}