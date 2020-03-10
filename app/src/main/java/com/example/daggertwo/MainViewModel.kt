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
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainViewModel @Inject constructor(var authApi: AuthApi, var sessionManager: SessionManager) : ViewModel() {
    @Inject
    lateinit var picasso: Picasso
//    lateinit var authUser: MediatorLiveData<ResourceResponse<Todo>>

//    lateinit var sessionManager: SessionManager

    init {
        Log.i("ViewModel", "viewModel is working")
        if (authApi == null) {
            Log.i("ViewModel", "AuthApi is null")
        } else {
            Log.i("ViewModel", "AuthApi is not null")
        }
//
//        var hl = "he"
//
//        val h = authApi.getTodo(2)
//            .toObservable()
//            .subscribeOn(Schedulers.io())
//            .blockingForEach {
//                hl += it.title
//            }

//        Log.i("hl", hl)

//        authUser = MediatorLiveData()

    }

    fun hello() {
        Log.i("ViewModel", "hello is working")
    }

    fun load(image: Int, imageView: ImageView) {
        picasso.load(image).into(imageView)
    }
    fun authenticateWithId(id:Int){
        Log.i("mainViewModel", "Attempting to login")
//        authUser.value = ResourceResponse.Loading<Todo>(Todo(), "Loading")
////        val source =
//
//        authUser.addSource(source) {
//            authUser.value = it
//            authUser.removeSource(source)
//        }

        sessionManager.authenticateWithId(queryTodoId(id))
    }

    fun queryTodoId(id: Int):LiveData<ResourceResponse<Todo>>{
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getTodo(id)
                .onErrorReturn {
                    val errorTodo = Todo()
                    errorTodo.id = -1
                    errorTodo
                }
                .map {todo ->
                    if(todo.id == -1){
                        ResourceResponse.Error<Todo>("Could not find todo", null)
                    }
                    else{
                        ResourceResponse.Success<Todo>(todo)
                    }

                }
                .subscribeOn(Schedulers.io())

        )
    }
    //
    fun observeTodoState(): LiveData<ResourceResponse<Todo>> {
        return sessionManager.getAuthTodo()
    }
//    fun updateUI(authApi: AuthApi): Todo {
//
//
//
//
//    }
}