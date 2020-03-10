package com.example.daggertwo

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AfterMainFragmentModule {

    @ContributesAndroidInjector
    abstract fun profileFragment():ProfileFragment
}