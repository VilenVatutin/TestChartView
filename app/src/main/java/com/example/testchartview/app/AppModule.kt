package com.example.testchartview.app

import android.app.Application
import android.content.Context
import com.example.testchartview.CryptoApi
import com.example.testchartview.URL
import com.example.testchartview.main.IMainActivity
import com.example.testchartview.main.MainActivity
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideRetorofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
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
    fun provdeMainActivity(): IMainActivity = MainActivity()


    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): CryptoApi = retrofit.create(CryptoApi::class.java)
}