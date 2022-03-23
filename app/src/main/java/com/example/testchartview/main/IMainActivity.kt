package com.example.testchartview.main

import com.example.chart.chart_view.data.InputData
import com.example.testchartview.model.Prices
import com.example.testchartview.model.PricesDao

interface IMainActivity {

    fun showData(prices: List<InputData>)
}