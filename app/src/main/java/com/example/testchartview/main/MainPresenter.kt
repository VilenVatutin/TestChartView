package com.example.testchartview.main

import javax.inject.Inject

class MainPresenter: IMainPresenter {

    @Inject
    lateinit var interactor: IMainInteractor

    fun getData(ticker: String){
        interactor.getData(ticker).subscribe({

        },{

        })
    }
}