package com.example.delivery1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.delivery1.fragments.About
import com.google.android.material.navigation.NavigationView

class DriverSide : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_side)

        /*------------------------------Navigation Drawer Menu--------------------------------*/
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout_driver)
        val navigationView : NavigationView =findViewById(R.id.nav_view_driver)
        val toolbar : androidx.appcompat.widget.Toolbar? = findViewById(R.id.toolbar)

        /*-------Tool Bar---------*/
        setSupportActionBar(toolbar);

        navigationView.bringToFront()
        toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean{
        val intent : Intent = Intent()
        val about = About()
        when (item.itemId){
            R.id.nav_profile -> {
                Toast.makeText(this,"Not done yet!", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                intent.setClass(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_share -> {
                Toast.makeText(this,"Copied!", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_about ->{
                makeCurrentFragment(about)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}