package com.example.testchartview.main

import io.reactivex.Single

interface IMainRepository {
    fun getData(ticker: String): Single<List<Double>>
}