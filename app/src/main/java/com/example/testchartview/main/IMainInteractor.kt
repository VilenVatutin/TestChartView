package com.example.testchartview.main

import com.example.testchartview.model.PricesDao
import io.reactivex.Single

interface IMainInteractor {

    fun getData(ticker: String): Single<PricesDao>
}