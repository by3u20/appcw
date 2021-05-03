package com.example.delivery1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.delivery1.fragments.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val deliveriesFragment = Deliveries()
        val driversFragment = Drivers()
        val vendorsFragment = Vendors()
        val buildingFragment = Building()
        val bottom_navigation = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)

        /*----usable---
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navView = findViewById<com.google.android.material.navigation.NavigationView>(R.id.nav_view)
        val aboutFragment = about()
        /---*/

        //*-------Change Fragment---------*/
        makeCurrentFragment(deliveriesFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.deliveries -> makeCurrentFragment(deliveriesFragment)
                R.id.drivers -> makeCurrentFragment(driversFragment)
                R.id.vendors -> makeCurrentFragment(vendorsFragment)
                R.id.buildingsites -> makeCurrentFragment(buildingFragment)
            }
            true
        }

        //*--------------------------------------------------------------------------
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navigationView : NavigationView =findViewById(R.id.nav_view)
        val toolbar : androidx.appcompat.widget.Toolbar? = findViewById(R.id.toolbar)

        /*-------Tool Bar---------*/
        setSupportActionBar(toolbar);

        /*-------Navigation Drawer Menu---------*/
        navigationView.bringToFront()
        toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        //Drawer-------------------------------------------------------------------*/

        /*----usable---
        toggle =  ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_settings -> makeCurrentFragment(aboutFragment)
                R.id.nav_profile -> makeCurrentFragment(aboutFragment)
                R.id.nav_logout -> makeCurrentFragment(aboutFragment)
                R.id.nav_share -> makeCurrentFragment(aboutFragment)
                R.id.nav_about -> makeCurrentFragment(aboutFragment)
            }
            true
        }
        /------*/



    }


    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}

