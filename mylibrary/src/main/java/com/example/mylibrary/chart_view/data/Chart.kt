package com.example.mylibrary.chart_view.data

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import java.util.ArrayList

class Chart {
    var width = 0
    var height = 0

    var lineWidth = 1f
        set(value) {
            field = value
            linePaint.strokeWidth = field
        }
    var lineColor = Color.BLUE
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

    var frameLinePaint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 1f
        color = Color.GRAY
    }
    var isFrameNeeded = true

    var padding = 0
    var textSize = 0
    var heightOffset = 0
    var pointPosition = 0

    var radius = 0
    var innerRadius = 0
    var pointDrawable:Drawable? = null


    var inputData: ArrayList<InputData> = ArrayList<InputData>()
    var showingData: ArrayList<InputData> = ArrayList<InputData>()
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

        const val CHART_PARTS = 5
        const val MAX_ITEMS_COUNT = 30
        const val CHART_PART_VALUE = 100
        const val TEXT_OFFSET = 20
    }
}