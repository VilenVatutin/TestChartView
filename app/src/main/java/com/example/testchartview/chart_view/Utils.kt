package com.example.chart.chart_view

import com.example.chart.chart_view.data.Chart
import com.example.chart.chart_view.data.DrawData
import com.example.chart.chart_view.data.InputData
import com.example.testchartview.chart_view.data.PointData
import com.example.testchartview.chart_view.data.PointDrawData
import java.util.*
import kotlin.math.abs

object Utils {

    fun max(dataList: List<InputData>?): Double {
        var maxValue = 0.0
        if (dataList == null || dataList.isEmpty()) {
            return maxValue
        }
        for (data in dataList) {
            if (data.graphValue > maxValue) {
                maxValue = data.graphValue
            }
        }
        return maxValue
    }

    fun clickOnPoint(x: Float, y: Float, chart: Chart): PointDrawData {
        for (i: Int in 0..chart.points.lastIndex) {
            if (abs(abs(chart.points[i].x) - abs(x)) < 30f && abs(abs(chart.points[i].y) - abs(y)) <= 30f) {
                return PointDrawData(chart.points[i].x, chart.points[i].y, chart.points[i].info, i)
            }
        }
        return PointDrawData(-1f, -1f, "", -1)
    }

    fun moveOnPoint(x: Float, chart: Chart): PointDrawData {
        for (i: Int in 0..chart.points.lastIndex) {
            if (abs(abs(chart.points[i].x) - abs(x)) < 30f) {
                return PointDrawData(chart.points[i].x, chart.points[i].y, chart.points[i].info, i)
            }
        }
        return PointDrawData(-1f, -1f, "", -1)
    }

    fun getCorrectedMaxValue(maxValue: Double): Double {
        for (value in (maxValue * 100).toInt() downTo Chart.CHART_PART_VALUE) {
            if (value % Chart.CHART_PART_VALUE == 0) {
                return value.toDouble() / 100
            }
        }
        return maxValue
    }

    fun getCorrectedMinValue(minValue: Double): Double {
        for (value in (minValue * 100).toInt()..Chart.CHART_PART_VALUE) {
            if (value % Chart.CHART_PART_VALUE == 0) {
                return value.toDouble() / 100
            }
        }
        return minValue
    }

    fun getOffPoint(mOffset: Float, viewWidth: Int): Int =
        -1 * (mOffset / (viewWidth / Chart.MAX_ITEMS_COUNT)).toInt()

    fun getDrawData(
        mOffset: Float,
        viewWidth: Int,
        chart: Chart?,
        position: Int? = null,
    ): List<DrawData> {// возвращает координаты рисования
        if (chart == null || chart.inputData.isEmpty()) {
            return ArrayList<DrawData>()
        }
        val offPoint = getOffPoint(mOffset, viewWidth)
        val to = Chart.MAX_ITEMS_COUNT + offPoint + chart.offPoint
        val from = chart.offPoint + offPoint
        if (from < 0) return chart.drawData

        if (to <= chart.inputData.lastIndex ||
            (to > 0 && offPoint < 0)
        ) {
            chart.showingData = (chart.inputData.subList(from, to).toList()) as ArrayList<InputData>
            return createDrawDataList(
                chart,
                createValueList(chart.showingData)
            )
        }
        return if (chart.offPoint >= 0 && Chart.MAX_ITEMS_COUNT + chart.offPoint < chart.inputData.lastIndex) {
            createDrawDataList(
                chart,
                createValueList(
                    chart.inputData.subList(
                        chart.offPoint,
                        Chart.MAX_ITEMS_COUNT + chart.offPoint
                    )
                )
            )
        } else {
            chart.drawData
        }
    }

    private fun createValueList(dataList: List<InputData>): List<Float> {
        val valueList: MutableList<Float> = ArrayList()
        val minValue = dataList.minByOrNull { it.graphValue }?.graphValue!!
        val topValue = max(dataList) - minValue
        for (data in dataList) {
            val value = ((data.graphValue - minValue) / topValue).toFloat()
            valueList.add(value)
        }
        return valueList
    }

    private fun createDrawDataList(
        chart: Chart,
        valueList: List<Float>
    ): List<DrawData> {
        val drawDataList: MutableList<DrawData> = ArrayList()
        for (i in 0 until valueList.lastIndex) {
            val drawData: DrawData = createDrawData(chart, valueList, i)
            drawDataList.add(drawData)
        }
        return drawDataList
    }

    private fun createDrawData(
        chart: Chart,
        valueList: List<Float>,
        position: Int
    ): DrawData {
        val drawData = DrawData()
        if (position > valueList.lastIndex) {
            return drawData
        }
        val value = valueList[position]
        val startX: Int = getCoordinateX(chart, position)
        val startY: Int = getCoordinateY(chart, value)
        drawData.startX = startX.toFloat()
        drawData.startY = startY.toFloat()
        val nextPosition = position + 1
        if (nextPosition < valueList.size) {
            val nextValue = valueList[nextPosition]
            val stopX: Int =
                getCoordinateX(chart, nextPosition)
            val stopY = getCoordinateY(chart, nextValue)
            drawData.stopX = stopX.toFloat()
            drawData.stopY = stopY.toFloat()
        }
        return drawData
    }


    private fun getCoordinateX(chart: Chart, index: Int): Int {
        val partWidth = chart.width / (Chart.MAX_ITEMS_COUNT-2)
        var coordinate = partWidth * index
        if (coordinate < 0) {
            coordinate = 0
        } else if (coordinate > chart.width) {
            coordinate = chart.width
        }
        return coordinate
    }

    private fun getCoordinateY(chart: Chart, value: Float): Int {
        val height: Int = chart.height - chart.padding - chart.textSize
        val heightOffset: Int = chart.heightOffset
        val heightCorrected = height - heightOffset
        var coordinate = (heightCorrected - heightCorrected * value).toInt()
        if (coordinate < 0) {
            coordinate = 0
        } else if (coordinate > heightCorrected) {
            coordinate = heightCorrected
        }
        coordinate += heightOffset
        return coordinate
    }


}