package com.example.mylibrary.chart_view.data

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import java.util.ArrayList

class Chart {
    var width = 0
    var height = 0

    var lineWidth = 1f // толщина линии графика
        set(value) {
            field = value
            linePaint.strokeWidth = field
        }
    var lineColor = Color.BLUE//цвет линии графика
        set(value) {
            field = value
            linePaint.color = field
        }

    var linePaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        color = lineColor
        strokeWidth = lineWidth
    }

    var frameLinePaint = Paint().apply {//цвет линий шкалы
        isAntiAlias = true
        strokeWidth = 1f
        color = Color.GRAY
    }
    var isFrameNeeded = true // нужно ли рисовать шкалу

    var padding = 0
    var textSize = 0
    var heightOffset = 0
    var pointPosition = 0

    var radius = 0
    var innerRadius = 0
    var pointDrawable:Drawable? = null


    var inputData: ArrayList<InputData> = ArrayList<InputData>() // данные для отображения
    var showingData: ArrayList<InputData> = ArrayList<InputData>()//данные которые будут отображаться на текущей странице
    var points = MutableList(MAX_ITEMS_COUNT) { PointData() }
    var offPoint: Int = 0
        set(value) {
            field = when {
                value <= 0 -> 0
                value > inputData.lastIndex - MAX_ITEMS_COUNT -> inputData.lastIndex - MAX_ITEMS_COUNT
                else -> value
            }
        }
    var drawData: ArrayList<DrawData> = ArrayList<DrawData>()

    companion object {

        const val CHART_PARTS = 5 // количество горизонтальных линий на шкале
        const val MAX_ITEMS_COUNT = 30//количество элементов на одной страннице графика
        const val CHART_PART_VALUE = 100// округление значений цены
        const val TEXT_OFFSET = 20// насколько текст находится выше линий шкалы
    }
}