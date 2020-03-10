package com.example.daggertwo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_after_main.*

class AfterMainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        getFragment()

        Toast.makeText(this, "Profile Fragment", Toast.LENGTH_SHORT).show()


        setContentView(R.layout.activity_after_main)

        val navController =  Navigation.findNavController(this, R.id.fragment2)
        NavigationUI.setupActionBarWithNavController(this, navController, nav_drawer_layout)
        NavigationUI.setupWithNavController(navigation, navController)
        navigation.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.aftermainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){

            R.id.logout -> sessionManager.logout()
            android.R.id.home -> nav_drawer_layout.openDrawer(GravityCompat.START)

        }
        return super.onOptionsItemSelected(item)

    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_profile -> Toast.makeText(this, "Profile nav clicked", Toast.LENGTH_SHORT).show()
            R.id.nav_post -> Toast.makeText(this, "Post nav clicked", Toast.LENGTH_SHORT).show()
        }
        item.setChecked(true)
        nav_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}