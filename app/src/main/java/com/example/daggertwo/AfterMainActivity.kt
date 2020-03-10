package com.example.daggertwo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class AfterMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getFragment()

        Toast.makeText(this, "Profile Fragment", Toast.LENGTH_SHORT).show()

        setContentView(R.layout.activity_after_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.aftermainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){

            R.id.logout -> sessionManager.logout()

        }
        return super.onOptionsItemSelected(item)

    }

    fun getFragment(){
        val sup = supportFragmentManager.beginTransaction()
        sup.replace(R.id.fragment, ProfileFragment())
        sup.commit()
    }
}