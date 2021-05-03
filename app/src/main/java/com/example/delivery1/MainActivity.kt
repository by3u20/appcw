package com.example.delivery1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.delivery1.fragments.Building
import com.example.delivery1.fragments.Deliveries
import com.example.delivery1.fragments.Drivers
import com.example.delivery1.fragments.Vendors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val deliveriesFragment = Deliveries()
        val driversFragment = Drivers()
        val vendorsFragment = Vendors()
        val buildingFragment = Building()
        val bottom_navigation = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)

        makeCurrentFragment(deliveriesFragment)

        bottom_navigation.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.deliveries -> makeCurrentFragment(deliveriesFragment)
                R.id.drivers -> makeCurrentFragment(driversFragment)
                R.id.vendors -> makeCurrentFragment(vendorsFragment)
                R.id.buildingsites -> makeCurrentFragment(buildingFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

}

