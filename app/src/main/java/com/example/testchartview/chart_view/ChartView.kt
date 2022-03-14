package com.example.chart.chart_view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.DragEvent
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import com.example.chart.chart_view.data.Chart
import com.example.chart.chart_view.data.InputData
import java.util.ArrayList

class ChartView(viewContext: Context, attrs: AttributeSet) : View(viewContext, attrs) {

    var firstPosition: Float = 0f

    private val chartManager = ChartManager(viewContext)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec) / 2
        chartManager.chart.height = height
        chartManager.chart.width = width
        setMeasuredDimension(width, height)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var offset: Float = 0f
        var chart = chartManager.chart
        when(event?.action){
            ACTION_DOWN ->{
                firstPosition = event.rawX
            }
            ACTION_MOVE ->{
                offset = event.rawX -  firstPosition
//                print("${event.rawX} $firstPosition  \n")
                chart.drawData = (Utils().getDrawData(offset,this.width, chart))
                invalidate()
            }
            ACTION_UP ->{
                //  положить offset в chart
            }
            else ->{}
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        chartManager.drawManager.draw(canvas)
    }
    fun setData(dataList: List<InputData>) {
        val chart: Chart = chartManager.chart
        chart.inputData = dataList
        chartManager.drawManager.updateTitleWidth()
        post {
            chart.drawData = (Utils().getDrawData(0f,this.width, chart))
//            chartManager.animate()
        }
    }
}