package com.example.chart.chart_view

import com.example.chart.chart_view.data.Chart
import com.example.chart.chart_view.data.DrawData
import com.example.chart.chart_view.data.InputData
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class Utils {

    fun max(dataList: List<InputData>?): Int {
        var maxValue = 0
        if (dataList == null || dataList.isEmpty()) {
            return maxValue
        }
        for (data in dataList) {
            if (data.value > maxValue) {
                maxValue = data.value
            }
        }
        return maxValue
    }

    fun clickOnPoint(x: Float, y: Float, chart: Chart): Pair<Pair<Float, Float>, String>{
        for(i: Int in 0..chart.points.lastIndex){
            if(abs(abs(chart.points[i].x) - abs(x)) <30f && abs(abs(chart.points[i].y) - abs(y)) <=30f){
                return Pair(Pair(chart.points[i].x, chart.points[i].y),chart.points[i].info)
//                print(" ...........${chart.points[i].info} .....\n" +
//                        "............\n")
            }
        }
        return Pair(Pair(-1f,-1f), "")
    }

    fun moveOnPoint(x: Float, chart: Chart): Pair<Pair<Float, Float>, String>{
        for(i: Int in 0..chart.points.lastIndex){
            if(abs(abs(chart.points[i].x) - abs(x)) <30f){
                return Pair(Pair(chart.points[i].x, chart.points[i].y), chart.points[i].info)
            }
        }
        return Pair(Pair(-1f,-1f), "")
    }

    fun getCorrectedMaxValue(maxValue: Int): Int { // берет наименьший общий делитель, чтобы построить шкалу
        for (value in maxValue downTo Chart.CHART_PART_VALUE) {
            if (value % Chart.CHART_PARTS == 0) {
                return value
            }
        }
        return maxValue
    }
    fun format(millis: Long): String {
        val format = SimpleDateFormat("dd", Locale.getDefault())
        return format.format(millis)
    }

    fun getOffPoint(mOffset: Float, viewWidth: Int ): Int = -1*(mOffset/(viewWidth/Chart.MAX_ITEMS_COUNT)).toInt()

    fun getDrawData(mOffset: Float,viewWidth: Int, chart: Chart?): List<DrawData> {// возвращает координаты рисования
        if (chart == null || chart.inputData.isEmpty()) {
            return ArrayList<DrawData>()
        }
        var offPoint = getOffPoint(mOffset, viewWidth)
        if(offPoint+chart.offPoint >= 0 &&  offPoint+chart.offPoint <=  chart.inputData.lastIndex){
            chart.showingData =  chart.inputData.subList(offPoint+chart.offPoint, Chart.MAX_ITEMS_COUNT+offPoint+chart.offPoint)
            correctDataListSize(chart.showingData)
            return createDrawDataList(
                chart,
                createValueList(chart.showingData)
            )
        }
        if(offPoint< 0 && chart.offPoint + offPoint > 0){
            chart.showingData =  chart.inputData.subList(chart.offPoint+offPoint, Chart.MAX_ITEMS_COUNT+chart.offPoint+offPoint)
            correctDataListSize(chart.showingData)
            return createDrawDataList(
                chart,
                createValueList(chart.showingData)
            )
        }
        //print("${chart.inputData.subList(abs(offPoint), Chart.MAX_ITEMS_COUNT+abs(offPoint))} \n")

        //chart.offPoint может быть отрицательным
        return if(chart.offPoint >= 0){
            correctDataListSize(chart.inputData.subList(chart.offPoint, Chart.MAX_ITEMS_COUNT+chart.offPoint))
            createDrawDataList(
                chart,
                createValueList(chart.inputData.subList( chart.offPoint, Chart.MAX_ITEMS_COUNT+chart.offPoint))
            )
        } else {
            chart.drawData
        }
    }

    private fun createValueList(dataList: List<InputData>): List<Float> {
        val valueList: MutableList<Float> = ArrayList()
        val minValue = dataList.minByOrNull { it.value }?.value!!
        val topValue: Int = Utils().max(dataList) - minValue
        for (data in dataList) {
            val value = (data.value.toFloat()- minValue)/ topValue
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
        drawData.startX = startX.toFloat() // убрал прибавление offSet и это исправило баг
        drawData.startY =  startY.toFloat()
        val nextPosition = position + 1
        if (nextPosition < valueList.size) {
            val nextValue = valueList[nextPosition]
            val stopX: Int =
                getCoordinateX(chart, nextPosition)
            val stopY = getCoordinateY(chart, nextValue)
            drawData.stopX = stopX.toFloat()
//            print("$startX $stopX $mOffset \n")
            drawData.stopY = stopY.toFloat()
        }
        return drawData
    }


    private fun correctDataListSize(dataList: List<InputData>) {
        if (dataList.size < Chart.MAX_ITEMS_COUNT) {
            addLackingItems(dataList.toMutableList())
        } else if (dataList.size > Chart.MAX_ITEMS_COUNT) {
            removeExcessItems(dataList.toMutableList())
        }
    }
    private fun addLackingItems(dataList: MutableList<InputData>) {
        for (i in dataList.size until Chart.MAX_ITEMS_COUNT) {
            var millis: Long = dataList[0].millis - TimeUnit.DAYS.toMillis(1)
            if (millis < 0) {
                millis = 0
            }
            dataList.add(0, InputData(0, millis))
        }
    }

    private fun getCoordinateX(chart: Chart, index: Int): Int {
        val partWidth = chart.width / (Chart.MAX_ITEMS_COUNT-1)
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

    private fun removeExcessItems(dataList: MutableList<InputData>) {
        val iterator = dataList.listIterator()
        while (iterator.hasNext()) {
            if (iterator.nextIndex() > Chart.MAX_ITEMS_COUNT) {
                iterator.remove()
                return
            }
            iterator.next()
        }
    }
}