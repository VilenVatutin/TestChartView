package com.example.mylibrary.chart_view

interface IChartView {
    fun setOnPointChosenListener(listener: OnPointChosenLitener)
    fun removeOnPointChosenListener()
}