package com.example.testchartview.app

import com.example.testchartview.ChartModule
import com.example.testchartview.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ChartModule::class]
)
interface AppComponent {

    fun inject(target: MainActivity)
}