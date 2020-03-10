package com.example.daggertwo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(includes = [ProviderModule::class])
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel):ViewModel


//
//    @Binds
//    abstract fun viewModelFactory2(viewModelProviderFactory: ViewModelProviderFactory):ViewModelProvider.Factory
}

@Module
internal object ProviderModule{
    @Provides
    fun viewModelFactory(creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>):ViewModelProvider.Factory{
        return ViewModelProviderFactory(creators)
    }
}