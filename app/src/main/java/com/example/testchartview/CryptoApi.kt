package com.example.testchartview

import io.reactivex.Single
import retrofit2.http.GET

interface CryptoApi {

    @GET("")
    fun getData(): Single<List<Double>>

}