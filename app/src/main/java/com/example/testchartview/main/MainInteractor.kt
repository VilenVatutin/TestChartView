package com.example.testchartview.main

import com.example.testchartview.model.PricesDao
import io.reactivex.Single
import javax.inject.Inject

class MainInteractor @Inject constructor(private var repository: IMainRepository): IMainInteractor {

    override fun getData(ticker: String): Single<PricesDao> = repository.getData(ticker)
}