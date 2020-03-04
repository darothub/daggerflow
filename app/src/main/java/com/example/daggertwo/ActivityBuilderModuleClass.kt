package com.example.daggertwo

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module(includes = [TheSecondModule::class])
abstract class ActivityBuilderModuleClass {


    @ContributesAndroidInjector(
        modules = [MainViewModelModule::class]
    )
    abstract fun mainActivity():MainActivity

}

@Module
internal object TheSecondModule{
    @Singleton
    @Provides
    @JvmStatic
   fun requestOption():RequestOptions{
        return RequestOptions
            .placeholderOf(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)

    }
    @Singleton
    @Provides
    @JvmStatic
    fun requestManager(application: Application, requestOptions: RequestOptions):RequestManager{
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun okhttpDownloader(client: OkHttpClient):OkHttp3Downloader{
        return OkHttp3Downloader(client)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun okhttpClientProvider():OkHttpClient{
        return OkHttpClient()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun picassoProvider(context: Context, okHttp3Downloader: OkHttp3Downloader):Picasso{
        return Picasso.Builder(context).loggingEnabled(true).build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun drawableProvider(application: Application):Drawable{
        return ContextCompat.getDrawable(application, R.drawable.pic)!!
    }

    @Singleton
    @Provides
    @JvmStatic
    fun drawableProvider2():ArrayList<Int>{
        return arrayListOf(R.drawable.pic)
    }

    @Singleton
    @Provides
    @JvmStatic
    fun getContexts(application: Application):Context{
        return application.applicationContext
    }
}