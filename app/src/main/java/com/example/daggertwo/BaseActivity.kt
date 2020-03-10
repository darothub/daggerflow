package com.example.daggertwo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeObserver()
    }

    private fun subscribeObserver(){
        sessionManager.getAuthTodo().observe(this, Observer {
            when(it){
                is ResourceResponse.Loading ->{
//                    progressBar.show()
//                    btn.hide()
                    Log.i("Loading", "${it.message}")
                }
                is ResourceResponse.Error ->{
//                    progressBar.hide()
//                    btn.show()
                    Log.i("Error", "${it.message}")
                }
                is ResourceResponse.Success ->{
//                    progressBar.hide()
//                    btn.show()
                    Log.i("Success", "${it.data}")
                }

                is ResourceResponse.Logout -> {
                    navLoginScreen()
                    Log.i("BaseAct", "${it.message}")
                }

            }
        })
    }

    private fun navLoginScreen(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}