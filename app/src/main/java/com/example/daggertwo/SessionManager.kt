package com.example.daggertwo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.example.daggertwo.model.Todo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SessionManager @Inject constructor() {

    private var cachedTodo: MediatorLiveData<ResourceResponse<Todo>> = MediatorLiveData()

    fun authenticateWithId(source:LiveData<ResourceResponse<Todo>>){
        if(cachedTodo != null){
            cachedTodo.value = ResourceResponse.Loading(Todo(), "Loading...")
            cachedTodo.addSource(source){
                cachedTodo.value = it
                cachedTodo.removeSource(source)
            }
        }


//        cachedTodo.addSource(source) {
//            cachedTodo.value = it
//            cachedTodo.removeSource(source)
//        }
    }

    fun logout(){
        Log.i("Todo", "log out")
        cachedTodo.value = ResourceResponse.Logout("Logging out", null)
    }

    fun getAuthTodo():LiveData<ResourceResponse<Todo>>{
        return cachedTodo
    }
}