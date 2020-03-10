package com.example.daggertwo

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject




class MainActivity : DaggerAppCompatActivity() {


    lateinit var viewModel: MainViewModel

//    @Inject
//    lateinit var authApi: AuthApi

    @Inject
    lateinit var pic:Drawable

    @Inject
    lateinit var pixels:ArrayList<Int>

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setImage2()
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
        viewModel.hello()
        viewModel.load(pixels.get(0), imagePlace)

//        val ress = viewModel.updateUI(authApi)

//        Log.i("result", "${ress.title}")


        authenticate()
        observeUser()

    }

    private fun setImage(){
        requestManager.load(pic).into(imagePlace)
    }

    private fun setImage2(){
        picasso.load(pixels.get(0)).into(imagePlace)
//        Picasso.get().load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg").into(imagePlace)
    }

    private fun authenticate(){


        btn.setOnClickListener {
            val input = inputView.text.toString().trim()
            if(TextUtils.isEmpty(input)){
                Toast.makeText(this, "invalid input", Toast.LENGTH_LONG).show()
            }
            else{
//                viewModel.authenticateWithId(input.toInt())
                onLoginSuccess()
            }
        }

        btn.invalidate()
    }

    private fun observeUser(){
        viewModel.observeTodoState().observe(this, Observer {
            when(it){
                is ResourceResponse.Loading ->{
                    progressBar.show()
                    btn.hide()
                    Log.i("Loading", "${it.message}")
                }
                is ResourceResponse.Error ->{
                    progressBar.hide()
                    btn.show()
                    Log.i("Error", "${it.message}")
                }
                is ResourceResponse.Success ->{
                    progressBar.hide()
                    btn.show()
                    onLoginSuccess()
                    Log.i("Success", "${it.data}")
                }

            }
        })
    }

    private fun onLoginSuccess(){
        var intent = Intent(this, AfterMainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun View.hide(){
        this.visibility = View.GONE
    }
    private fun View.show(){
        this.visibility = View.VISIBLE
    }
}
