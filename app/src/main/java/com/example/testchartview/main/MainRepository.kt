package com.example.testchartview.main

import com.example.testchartview.CryptoApi
import com.example.testchartview.model.PricesDao
import io.reactivex.Single
import javax.inject.Inject

class MainRepository: IMainRepository {
    @Inject
    lateinit var api: CryptoApi
    override fun getData(id: String): Single<PricesDao> = api.getData(id)


}