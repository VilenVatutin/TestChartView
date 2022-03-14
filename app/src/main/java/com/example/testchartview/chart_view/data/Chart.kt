package com.example.chart.chart_view.data

import java.util.ArrayList

class Chart {



    var width = 0
    var height = 0

    var padding = 0
    var titleWidth = 0
    var textSize = 0
    var heightOffset = 0

    var radius = 0
    var innerRadius = 0

    var inputData: List<InputData> = ArrayList<InputData>()
    var showingData: List<InputData> = ArrayList<InputData>()
    var offPoint: Int = 0
    var drawData: List<DrawData> = ArrayList<DrawData>()

    companion object{

        const val CHART_PARTS = 10 //сколько длелений показывается
        const val MAX_ITEMS_COUNT = 6
        const val CHART_PART_VALUE = 10 // сколько горизонтальных делений
        const val TEXT_SIZE_OFFSET = 10
    }
}