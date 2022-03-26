package com.example.mylibrary.chart_view

import android.content.Context
import android.graphics.Canvas
import com.example.mylibrary.chart_view.DrawController
import com.example.mylibrary.chart_view.data.Chart

class DrawManager(context: Context) {
    val chart = Chart()
    private val drawController = DrawController(context, chart)

    fun draw(canvas: Canvas){
        drawController.draw(canvas)
    }
}