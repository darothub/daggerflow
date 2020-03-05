package com.example.daggertwo

import com.example.daggertwo.model.Todo
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject


interface AuthApi {

    @GET("todos/{id}")
    fun getTodo(@Path("id") id:Int): Flowable<Todo>
}