package com.example.daggertwo

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel@Inject constructor() : ViewModel() {
    init {
        Log.i("ViewModel", "viewModel is working")
    }
}