package com.example.daggertwo

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface AuthApi {

    @GET
    fun fakeStuff(): Call<ResponseBody>
}