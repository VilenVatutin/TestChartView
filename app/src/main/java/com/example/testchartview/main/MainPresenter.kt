package com.example.testchartview.main

import android.annotation.SuppressLint
import com.example.chart.chart_view.data.InputData
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainPresenter @Inject constructor(private var interactor: IMainInteractor): IMainPresenter {

    lateinit var view: IMainActivity


    override fun injectView(view: IMainActivity){
        this.view = view
    }



    @SuppressLint("CheckResult")
    override fun getData(ticker: String){
        interactor.getData(ticker)
            .map {
                val list = mutableListOf<InputData>()
                it.prices.forEach { price ->
                    list.add(InputData(price[1],"07.06.2012"))
                }
                return@map list
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe({
            view.showData(it)
        },{
            print(it)
        })
    }
}