package com.example.testchartview.chart_view

interface IChartView {
    fun setOnPointChosenListener(listener: OnPointChosenLitener)
    fun removeOnPointChosenListener()
}