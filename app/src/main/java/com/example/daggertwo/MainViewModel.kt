package com.example.daggertwo

import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.daggertwo.model.Todo
import com.squareup.picasso.Picasso
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainViewModel @Inject constructor(var authApi: AuthApi) : ViewModel() {
    @Inject
    lateinit var picasso: Picasso
    lateinit var authUser: MediatorLiveData<Todo>

    init {
        Log.i("ViewModel", "viewModel is working")
        if (authApi == null) {
            Log.i("ViewModel", "AuthApi is null")
        } else {
            Log.i("ViewModel", "AuthApi is not null")
        }

        var hl = "he"

        val h = authApi.getTodo(2)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .blockingForEach {
                hl += it.title
            }

        Log.i("hl", hl)

        authUser = MediatorLiveData()

    }

    fun hello() {
        Log.i("ViewModel", "hello is working")
    }

    fun load(image: Int, imageView: ImageView) {
        picasso.load(image).into(imageView)
    }
    fun authenticateWithId(id:Int){
        val source = LiveDataReactiveStreams.fromPublisher(
            authApi.getTodo(id)
                .subscribeOn(Schedulers.io())

        )

        authUser.addSource(source) {
            authUser.value = it
            authUser.removeSource(source)
        }
    }

    //
    fun observeTodo(): LiveData<Todo> {
        return authUser
    }
//    fun updateUI(authApi: AuthApi): Todo {
//
//
//
//
//    }
}