package com.example.daggertwo

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityBuilderModuleClass::class, AndroidSupportInjectionModule::class])
interface BodyComponent : AndroidInjector<BaseApplication>{
        fun sessionManager():SessionManager

        @Component.Builder
        interface Builder{
            @BindsInstance
            fun application(application: Application):Builder

            fun build():BodyComponent
        }
}


