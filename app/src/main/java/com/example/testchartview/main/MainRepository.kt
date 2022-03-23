package com.example.testchartview.main

import com.example.testchartview.CryptoApi
import io.reactivex.Single
import javax.inject.Inject

class MainRepository: IMainRepository {
    @Inject
    lateinit var api: CryptoApi
    override fun getData(ticker: String): Single<List<Double>> = api.getData()


}