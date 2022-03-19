package com.example.chart.chart_view.data

import android.graphics.Paint
import com.example.testchartview.chart_view.data.PointData
import java.util.ArrayList

class Chart {


    var width = 0
    var height = 0

    var linePaint: Paint? = null

    var padding = 0
    var titleWidth = 0
    var textSize = 0
    var heightOffset = 0

    var radius = 0
    var innerRadius = 0

    var inputData: List<InputData> = ArrayList<InputData>()
    var showingData: List<InputData> = ArrayList<InputData>()
    var points = MutableList(MAX_ITEMS_COUNT) { PointData() }
    var offPoint: Int = 0
        set(value) { field = if(value < 0) 0 else value }
    var drawData: List<DrawData> = ArrayList<DrawData>()

    companion object {

        const val CHART_PARTS = 10 //сколько  горизонтальных линий
        const val MAX_ITEMS_COUNT = 22
        const val CHART_PART_VALUE = 10 // сколько горизонтальных делений
        const val TEXT_SIZE_OFFSET = 0
    }
}