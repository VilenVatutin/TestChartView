package com.example.testchartview.main

import com.example.testchartview.model.PricesDao
import io.reactivex.Single

interface IMainRepository {
    fun getData(ticker: String): Single<PricesDao>
}