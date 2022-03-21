package com.example.chart.chart_view

import android.content.Context
import android.graphics.Canvas
import com.example.chart.chart_view.data.Chart

class DrawManager(context: Context) {
    val chart = Chart()
    private val drawController = DrawController(context, chart)

    fun draw(canvas: Canvas){
        drawController.draw(canvas)
    }
}