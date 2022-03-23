package com.example.testchartview.main

import com.example.chart.chart_view.data.InputData
import javax.inject.Inject

class MainPresenter: IMainPresenter {

    @Inject
    lateinit var interactor: IMainInteractor

    lateinit var view: IMainActivity


    override fun injectView(view: IMainActivity){
        this.view = view
    }



    override fun getData(ticker: String){
        interactor.getData(ticker).subscribe({
            val list = mutableListOf<InputData>()
            it.prices.forEach { price ->
                list.add(InputData(price.second))
            }
            view.showData(list)
        },{
            print(it)
        })
    }
}