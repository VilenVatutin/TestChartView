package com.example.testchartview.main

import io.reactivex.Single

interface IMainInteractor {

    fun getData(ticker: String): Single<List<Double>>
}