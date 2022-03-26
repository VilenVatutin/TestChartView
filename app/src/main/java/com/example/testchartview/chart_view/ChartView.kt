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
import com.example.testchartview.model.PricesDao
import java.util.ArrayList

class ChartView(
    viewContext: Context,
    attrs: AttributeSet
) : View(viewContext, attrs), IChartView {

    private var firstPosition: Float = 0f
    private var offset: Float = 0f
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
        val bundle = Bundle().apply {
            putParcelable(SUPER_STATE, super.onSaveInstanceState())
            putParcelableArrayList(INPUT_DATA, chartManager.chart.inputData)
            putParcelableArrayList(SHOWING_DATA, chartManager.chart.showingData)
            putInt(OFFPOINT, chartManager.chart.offPoint)
            putParcelableArrayList(DRAW_DATA, chartManager.chart.drawData)
            putInt(LINE_COLOR, chartManager.chart.lineColor)
            putBoolean(IS_FRAME_NEEDED, chartManager.chart.isFrameNeeded)
        }
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
                isFrameNeeded = bundle.getBoolean(IS_FRAME_NEEDED)
            }
            superState = state.getParcelable(SUPER_STATE)
            invalidate()
        }
        super.onRestoreInstanceState(superState)
    }


    private fun setupAttributes(attrs: AttributeSet){
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.ChartView, 0, 0)
        chartManager.chart.apply {
            linePaint.color = typedArray.getInt(R.styleable.ChartView_lineColor, Color.BLUE)
            linePaint.strokeWidth = typedArray.getDimension(R.styleable.ChartView_lineWidth, 5.0f)
            frameLinePaint.color = typedArray.getInt(R.styleable.ChartView_frameLineColor, Color.GRAY)
            isFrameNeeded = typedArray.getBoolean(R.styleable.ChartView_isFrameNeeded, true)
            pointDrawable = typedArray.getDrawable(R.styleable.ChartView_point)
        }
        typedArray.recycle()

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
                        val point = Utils.clickOnPoint(event.x, event.y, chart)
                        chart.drawData =
                            (Utils.getDrawData(offset, this.width, chart)) as ArrayList<DrawData>
                        if (point.x > 0 && point.y > 0) {
                            chart.pointPosition = point.position
                            pointListener?.onPointChosen(
                                point.x.toInt(),
                                point.y.toInt(),
                                point.info
                            )
                        }
                        invalidate()
                    }
                    ACTION_MOVE -> {
                        val point = Utils.moveOnPoint(event.x, chart)
                        chart.pointPosition = point.position
                        pointListener?.onPointChosen(
                            point.x.toInt(),
                            point.y.toInt(),
                            point.info
                        )
                        invalidate()
                    }
                    ACTION_UP -> {
                        pointListener?.hideDialog()
                        chart.pointPosition = 0
                        invalidate()
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
                            (Utils.getDrawData(offset, this.width, chart)) as ArrayList<DrawData>
                        invalidate()
                    }
                    ACTION_POINTER_UP -> {
                        chart.offPoint += Utils.getOffPoint(offset, this.width)
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
        chartManager.chart.drawData = (Utils.getDrawData(0.0f, this.width, chartManager.chart)) as ArrayList<DrawData>
    }

    override fun onDraw(canvas: Canvas) {
        chartManager.drawManager.draw(canvas)
    }

    fun setData(dataList: List<InputData>) {
        val chart: Chart = chartManager.chart
        chart.offPoint = 0
        chart.inputData = dataList as ArrayList<InputData>
        post {
            chart.drawData = (Utils.getDrawData(0.0f, this.width, chart)) as ArrayList<DrawData>
            invalidate()
        }
    }

    fun hideFrame() {
        chartManager.chart.isFrameNeeded = false
        invalidate()
    }

    fun showFrame() {
        chartManager.chart.isFrameNeeded = true
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
        const val IS_FRAME_NEEDED = "draw frame"
    }
}