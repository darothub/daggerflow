package com.example.daggertwo

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject




class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

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

//        val component= DaggerBodyComponent.create()
//        component.inject(this)
//
//        body.drive()

//        Log.i("thisAct", s)
        setImage2()
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

    }

    private fun setImage(){
        requestManager.load(pic).into(imagePlace)
    }

    private fun setImage2(){
        picasso.load(pixels.get(0)).into(imagePlace)
//        Picasso.get().load("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg").into(imagePlace)
    }

}
