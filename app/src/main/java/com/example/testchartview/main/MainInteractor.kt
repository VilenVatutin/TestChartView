package com.example.testchartview.main

import io.reactivex.Single
import javax.inject.Inject

class MainInteractor: IMainInteractor {
    @Inject
    lateinit var repository: IMainRepository
    override fun getData(ticker: String): Single<List<Double>> = repository.getData(ticker)
}