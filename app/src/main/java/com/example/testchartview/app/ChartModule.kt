package com.example.testchartview.app

import com.example.testchartview.main.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ChartModule {

    @Singleton
    @Binds
    fun bindPresenter(presenter: MainPresenter): IMainPresenter

    @Singleton
    @Binds
    fun bindInteractor(interactor: MainInteractor): IMainInteractor

    @Singleton
    @Binds
    fun bindRepository(repository: MainRepository): IMainRepository

}