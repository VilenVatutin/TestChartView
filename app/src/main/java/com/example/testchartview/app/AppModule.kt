package com.example.testchartview.app

import android.app.Application
import android.content.Context
import com.example.testchartview.CryptoApi
import com.example.testchartview.MyApplication
import com.example.testchartview.URL
import com.example.testchartview.main.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val app: MyApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(URL.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideHttpLogging(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideMainActivity(): IMainActivity = MainActivity()

    @Provides
    @Singleton
    fun providePresenter(): IMainPresenter = MainPresenter()

    @Provides
    @Singleton
    fun provideRepository(): IMainRepository = MainRepository()

    @Provides
    @Singleton
    fun provideIntercator(): IMainInteractor = MainInteractor()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): CryptoApi = retrofit.create(CryptoApi::class.java)
}