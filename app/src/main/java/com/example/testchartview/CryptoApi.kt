package com.example.testchartview

import com.example.testchartview.model.PricesDao
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    @GET("coins/{id}/market_chart")
    fun getData(@Path("id") id: String,
                @Query("vs_currency") currency: String,
                @Query("days") days: String): Single<PricesDao>
}