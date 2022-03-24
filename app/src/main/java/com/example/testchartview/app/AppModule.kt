package com.example.testchartview.app

import android.content.Context
import com.example.testchartview.CryptoApi
import com.example.testchartview.MyApplication
import com.example.testchartview.URL
import com.example.testchartview.main.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideHttpLogging(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providePresenter(interactor: IMainInteractor): IMainPresenter = MainPresenter(interactor)
    @Provides
    @Singleton
    fun provideInteractor(repoository: IMainRepository): IMainInteractor = MainInteractor(repoository)

    @Provides
    @Singleton
    fun provideRepository(api: CryptoApi): IMainRepository = MainRepository(api)

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): CryptoApi = retrofit.create(CryptoApi::class.java)
}