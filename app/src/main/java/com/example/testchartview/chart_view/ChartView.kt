package com.example.chart.chart_view

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import com.example.chart.chart_view.data.Chart
import com.example.chart.chart_view.data.InputData
import com.example.testchartview.chart_view.IChartView
import com.example.testchartview.chart_view.OnPointChosenLitener

class ChartView(viewContext: Context,
                attrs: AttributeSet) : View(viewContext, attrs), IChartView {

    var firstPosition: Float = 0f
    var offset: Float = 0f
    var counter = 0
    var pointListener: OnPointChosenLitener? = null
    private var readyForSecondFinger = false

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
        var chart = chartManager.chart
//        when (event?.action) {
//            ACTION_DOWN -> {
//                firstPosition = event.rawX
//            }
//            ACTION_MOVE -> {
//                offset = event.rawX - firstPosition
//                chart.drawData = (Utils().getDrawData(offset, this.width, chart))
//                invalidate()
//            }
//            ACTION_UP -> {
//                chart.offPoint += Utils().getOffPoint(offset, this.width)
//            }
//            else -> {
//            }
//        }




//        when (event?.action) {
//            ACTION_DOWN -> {
//                firstPosition = event.x
//                val pair = Utils().clickOnPoint(event.x, event.y, chart)
//                pointListener?.onPointChosen(
//                    pair.first.first.toInt(),
//                    pair.first.second.toInt(),
//                    pair.second)
//            }
//            ACTION_MOVE -> {
//                val pair = Utils().moveOnPoint(event.x, chart)
//                pointListener?.onPointChosen(
//                    pair.first.first.toInt(),
//                    pair.first.second.toInt(),
//                    pair.second
//                )
//            }
//            ACTION_UP -> {
//                chart.offPoint += Utils().getOffPoint(offset, this.width)
//            }
//            else -> {
//            }
//        }

        event?.let {
            print("${it.pointerCount} \n")
            if (it.pointerCount == 1) {
                when (it.action) {
                    ACTION_DOWN -> {
                        firstPosition = event.x
                        val pair = Utils().clickOnPoint(event.x, event.y, chart)
                        if(pair.first.first > 0 && pair.first.second > 0){
                            pointListener?.onPointChosen(
                                pair.first.first.toInt(),
                                pair.first.second.toInt(),
                                pair.second
                            )
                        }
                    }
                    ACTION_MOVE -> {
                        val pair = Utils().moveOnPoint(event.x, chart)
                        pointListener?.onPointChosen(
                            pair.first.first.toInt(),
                            pair.first.second.toInt(),
                            pair.second
                        )
                    }
                    ACTION_UP -> {
                        chart.offPoint += Utils().getOffPoint(offset, this.width)
                        pointListener?.hideDialog()
                    }
                    else -> {
                    }
                }

            }
            if (it.pointerCount == 2) {
                val index =
                    it.action and ACTION_DOWN shr ACTION_MOVE
                when (it.action) {
                    ACTION_DOWN -> {

                        firstPosition = it.getX(it.getPointerId(index))
                        print("fp $firstPosition \n")
                    }
                    ACTION_MOVE -> {

                        offset = event.getX(it.getPointerId(index)) - firstPosition
                        chart.drawData = (Utils().getDrawData(offset, this.width, chart))
                        invalidate()
                    }
                    ACTION_UP -> {
                        chart.offPoint += Utils().getOffPoint(offset, this.width)
                    }
                    else -> {
                    }
                }
            }
        }
        return true
    }
    fun convertPxToDp(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

    override fun onDraw(canvas: Canvas) {
        chartManager.drawManager.draw(canvas)
    }

    fun setData(dataList: List<InputData>) {
        val chart: Chart = chartManager.chart
        chart.inputData = dataList
        chartManager.drawManager.updateTitleWidth()
        post {
            chart.drawData = (Utils().getDrawData(0f, this.width, chart))
//            chartManager.animate()
        }
    }

    override fun setOnPointChosenListener(listener: OnPointChosenLitener) {
        pointListener = listener
    }

    override fun removeOnPointChosenListener() {
        pointListener = null
    }
}