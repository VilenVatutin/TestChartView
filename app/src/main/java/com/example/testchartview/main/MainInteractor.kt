package com.example.testchartview.main

import com.example.testchartview.model.PricesDao
import io.reactivex.Single
import javax.inject.Inject

class MainInteractor: IMainInteractor {
    @Inject
    lateinit var repository: IMainRepository
    override fun getData(ticker: String): Single<PricesDao> = repository.getData(ticker)
}