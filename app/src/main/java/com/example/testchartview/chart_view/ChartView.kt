package com.example.testchartview.chart_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import com.example.chart.chart_view.ChartManager
import com.example.chart.chart_view.Utils
import com.example.chart.chart_view.data.Chart
import com.example.chart.chart_view.data.DrawData
import com.example.chart.chart_view.data.InputData
import com.example.testchartview.R
import java.util.ArrayList

class ChartView(
    viewContext: Context,
    attrs: AttributeSet
) : View(viewContext, attrs), IChartView {

    var firstPosition: Float = 0f
    var offset: Float = 0f
    var pointListener: OnPointChosenLitener? = null

    private val chartManager = ChartManager(viewContext)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec) / 2
        chartManager.chart.height = height
        chartManager.chart.width = width
        setMeasuredDimension(width, height)
    }


    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable(SUPER_STATE, super.onSaveInstanceState())
        bundle.putParcelableArrayList(INPUT_DATA, chartManager.chart.inputData)
        bundle.putParcelableArrayList(SHOWING_DATA, chartManager.chart.showingData)
        bundle.putInt(OFFPOINT, chartManager.chart.offPoint)
        bundle.putParcelableArrayList(DRAW_DATA, chartManager.chart.drawData)
        bundle.putInt(LINE_COLOR, chartManager.chart.lineColor)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var superState: Parcelable? = null
        if (state is Bundle) {
            val bundle = state
            chartManager.chart.apply {
                inputData = bundle.getParcelableArrayList(INPUT_DATA)!!
                showingData = bundle.getParcelableArrayList(SHOWING_DATA)!!
                offPoint = bundle.getInt(OFFPOINT)
                drawData = bundle.getParcelableArrayList(DRAW_DATA)!!
                lineColor = bundle.getInt(LINE_COLOR)
            }

            superState = state.getParcelable(SUPER_STATE)
            invalidate()
        }
        super.onRestoreInstanceState(superState)
    }


    private fun setupAttributes(attrs: AttributeSet){
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ChartView, 0, 0)
        chartManager.chart.linePaint.color = typedArray.getInt(R.styleable.ChartView_lineColor, Color.BLUE)
        chartManager.chart.linePaint.strokeWidth = typedArray.getDimension(R.styleable.ChartView_lineWidth, 5.0f)
        chartManager.chart.frameLinePaint.color = typedArray.getInt(R.styleable.ChartView_frameLineColor, Color.GRAY)
    }

    fun setLineColor(color: Int) {
        chartManager.chart.lineColor = color
    }

    fun setLineWidth(width: Float) {
        chartManager.chart.lineWidth = width
    }

    fun setFrameLineColor(color: Int){
        chartManager.chart.frameLinePaint.color= color
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var chart = chartManager.chart
        event?.let {
            if (it.pointerCount == 1) {
                when (it.action) {
                    ACTION_DOWN -> {
                        firstPosition = event.x
                        val pair = Utils().clickOnPoint(event.x, event.y, chart)
                        if (pair.first.first > 0 && pair.first.second > 0) {
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
                        pointListener?.hideDialog()
                    }
                    else -> {
                    }
                }

            }
            if (it.pointerCount == 2) {
                val index = it.action and ACTION_DOWN shr ACTION_MOVE
                when (it.actionMasked) {
                    ACTION_POINTER_DOWN -> {
                        firstPosition = it.getX(it.getPointerId(index))
                    }
                    ACTION_MOVE -> {

                        offset = event.getX(it.getPointerId(index)) - firstPosition
                        chart.drawData =
                            (Utils().getDrawData(offset, this.width, chart)) as ArrayList<DrawData>
                        invalidate()
                    }
                    ACTION_POINTER_UP -> {
                        chart.offPoint += Utils().getOffPoint(offset, this.width)
                    }
                    else -> {
                    }
                }
            }
        }
        return true
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        chartManager.chart.drawData = (Utils().getDrawData(0.0f, this.width, chartManager.chart)) as ArrayList<DrawData>
    }

    override fun onDraw(canvas: Canvas) {
        chartManager.drawManager.draw(canvas)
    }

    fun setData(dataList: List<InputData>) {
        val chart: Chart = chartManager.chart
        chart.offPoint = 0
        chart.inputData = dataList as ArrayList<InputData>
        chartManager.drawManager.updateTitleWidth()
        post {
            chart.drawData = (Utils().getDrawData(0.0f, this.width, chart)) as ArrayList<DrawData>
            invalidate()
        }
    }

    fun hideFrame() {
        chartManager.chart.drawFrame = false
        invalidate()
    }

    fun showFrame() {
        chartManager.chart.drawFrame = true
        invalidate()
    }

    override fun setOnPointChosenListener(listener: OnPointChosenLitener) {
        pointListener = listener
    }

    override fun removeOnPointChosenListener() {
        pointListener = null
    }
    init {
        setupAttributes(attrs)
    }

    companion object {
        const val INPUT_DATA = "input data"
        const val SHOWING_DATA = "showing data"
        const val SUPER_STATE = "super state"
        const val OFFPOINT = "offset"
        const val DRAW_DATA = "draw data"
        const val LINE_COLOR = "line color"
    }
}