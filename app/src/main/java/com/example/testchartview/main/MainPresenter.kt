package com.example.testchartview.main

import com.example.chart.chart_view.data.InputData
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainPresenter @Inject constructor(private var interactor: IMainInteractor): IMainPresenter {

//    @Inject
//    lateinit var interactor: IMainInteractor

    lateinit var view: IMainActivity


    override fun injectView(view: IMainActivity){
        this.view = view
    }



    override fun getData(ticker: String){
        interactor.getData(ticker).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            val list = mutableListOf<InputData>()
            it.prices.forEach { price ->
                list.add(InputData(price[1]))
            }
            view.showData(list)
        },{
            print(it)
        })
    }
}