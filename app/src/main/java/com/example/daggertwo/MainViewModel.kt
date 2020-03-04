package com.example.daggertwo

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainViewModel@Inject constructor(authApi: AuthApi) : ViewModel() {
    @Inject
    lateinit var picasso: Picasso
    init {
        Log.i("ViewModel", "viewModel is working")
        if(authApi == null){
            Log.i("ViewModel", "AuthApi is null")
        }
        else{
            Log.i("ViewModel", "AuthApi is not null")
        }
    }

    fun hello(){
        Log.i("ViewModel", "hello is working")
    }

    fun load(image:Int, imageView: ImageView){
        picasso.load(image).into(imageView)
    }
}