package com.example.daggertwo

import android.util.Log
import javax.inject.Inject


class Body @Inject constructor(val heart:Heart, val head:Head) {

    fun drive(){
        Log.i("car", "Drive...")
    }
}