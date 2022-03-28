package com.example.testchartview.main

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import com.example.mylibrary.chart_view.data.InputData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class MainPresenter @Inject constructor(private var interactor: IMainInteractor) : IMainPresenter {

    lateinit var view: IMainActivity


    override fun injectView(view: IMainActivity) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    override fun getData(ticker: String) {
        interactor.getData(ticker)
            .map {
                val list = mutableListOf<InputData>()
                it.prices.forEach { price ->
                    list.add(
                        InputData(
                            price[1],
                            SimpleDateFormat("yyyy-MM-dd").format(Date(price[0].toLong()))
                        )
                    )
                }
                return@map list
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showData(it)
            }, {
                print(it)
            })
    }
}