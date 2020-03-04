package com.example.daggertwo

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class AuthModule {
    @Provides
    fun provideAuthApi(retrofit: Retrofit):AuthApi{
        return retrofit.create(AuthApi::class.java)
    }

}

