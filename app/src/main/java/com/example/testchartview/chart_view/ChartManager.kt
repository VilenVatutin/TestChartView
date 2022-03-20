package com.example.chart.chart_view

import android.content.Context

class ChartManager(context: Context) {

    val drawManager = DrawManager(context)
    val chart = drawManager.chart
}