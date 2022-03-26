package com.example.testchartview

import android.app.Application
import com.example.testchartview.app.AppComponent
import com.example.testchartview.app.AppModule
import com.example.testchartview.app.DaggerAppComponent

class MyApplication: Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: MyApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()

}