package com.example.testchartview.app

import com.example.testchartview.main.IMainActivity
import com.example.testchartview.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(target: MainActivity)
}