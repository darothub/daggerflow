package com.example.daggertwo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggertwo.model.Todo
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var sessionManager: SessionManager) : ViewModel() {

    init {
        Log.i("ProfileViewModel", "Its is ready")
    }

    fun observeTodoStateToo(): LiveData<ResourceResponse<Todo>> {
        return sessionManager.getAuthTodo()
    }
}