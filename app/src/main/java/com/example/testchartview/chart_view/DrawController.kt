package com.example.chart.chart_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import com.example.chart.chart_view.data.Chart
import com.example.chart.chart_view.data.DrawData
import com.example.chart.chart_view.data.InputData
import com.example.testchartview.R
import com.example.testchartview.chart_view.Bezie
import com.example.testchartview.chart_view.data.PointData

class DrawController(
    val context: Context,
    val chart: Chart
) {
    private var frameTextPaint: Paint

    fun draw(canvas: Canvas) {
        drawPath(canvas)
        if (chart.isFrameNeeded) drawFrame(canvas)
    }

    private fun drawPath(canvas: Canvas) {
        val path = Bezie().getBeziePath(chart.drawData)
        canvas.drawPath(path, chart.linePaint)
        for (i in 0 until Chart.MAX_ITEMS_COUNT) {
            setPoints(canvas, i)
//            drawPoints(canvas, i)
        }
        if(chart.pointPosition != 0){
            drawPoints(canvas, chart.pointPosition)
        }
    }

    private fun drawFrame(canvas: Canvas) {
        drawChartVertical(canvas)
    }

    private fun drawChartVertical(canvas: Canvas) {
        val inputDataList: List<InputData> = chart.showingData
        if (inputDataList.isEmpty()) {
            return
        }
        val maxValue: Double = Utils.max(inputDataList)
        val minValue = inputDataList.minByOrNull { it.graphValue }?.graphValue!!
        val correctedMaxValue: Double = Utils.getCorrectedMaxValue(maxValue)
        val correctedMinValue = Utils.getCorrectedMinValue(minValue)
        val value = (correctedMaxValue - correctedMinValue) / (maxValue - minValue)
        val heightOffset: Int = chart.heightOffset
        val padding: Int = chart.padding
        val textSize: Int = chart.textSize
        val width: Float = chart.width.toFloat()
        val height: Float = (chart.height - textSize - padding).toFloat()
        val chartPartHeight: Float =
            ((height - heightOffset) * value / Chart.CHART_PARTS).toFloat()//
        var currHeight = height
        var currTitle = minValue
        for (i in 0..Chart.CHART_PARTS) {
            var titleY = currHeight - Chart.TEXT_OFFSET
            if (i <= 0) {
                titleY = height
            } else if (textSize + chart.heightOffset > currHeight) {
                titleY = currHeight + textSize
            }
            chart.frameLinePaint.color
            canvas.drawLine(
                padding.toFloat(), currHeight, width, currHeight,
                chart.frameLinePaint
            )
            val title = "\$" + String.format("%.2f", currTitle)
            canvas.drawText(title, padding.toFloat(), titleY, frameTextPaint!!)
            currHeight -= chartPartHeight
            currTitle += ((correctedMaxValue - correctedMinValue) / Chart.CHART_PARTS)
        }
    }

    private fun setPoints(canvas: Canvas, position: Int) {
        val dataList: List<DrawData> = chart.drawData
        if (position > dataList.lastIndex) {
            return
        }
        val drawData = dataList[position]
        val startX = drawData.startX
        val startY = drawData.startY
        val price = "\$" + String.format("%.2f", chart.showingData[position].graphValue)
        chart.points[position] = PointData(
            startX,
            startY,
            price +"\n"+chart.showingData[position].date
        )
//        if (position > 0) {
//            chart.pointDrawable?.let {
//                it.setBounds(
//                    startX.toInt() - it.intrinsicWidth / 2,
//                    startY.toInt() - it.intrinsicHeight / 2,
//                    startX.toInt() + it.intrinsicWidth / 2,
//                    startY.toInt() + it.intrinsicHeight / 2
//                )
//                it.draw(canvas)
//            }
//            chart.linePaint.alpha = alpha
//            canvas.drawCircle(startX, startY, radius.toFloat(), chart.linePaint)
//            canvas.drawCircle(startX, startY, inerRadius.toFloat(), chart.linePaint)
//        }
    }

    private fun drawPoints(
        canvas: Canvas,
        position: Int
    ) {
        if (position > 0) {
            val x  =chart.points[position].x
            val y = chart.points[position].y
            chart.pointDrawable?.let {
                it.setBounds(
                    x.toInt() - it.intrinsicWidth / 2,
                    y.toInt() - it.intrinsicHeight / 2,
                    x.toInt() + it.intrinsicWidth / 2,
                    y.toInt() + it.intrinsicHeight / 2
                )
                it.draw(canvas)
            }
        }
    }

    init {
        val res = context.resources
        with(chart) {
            heightOffset =
                ((res.getDimension(R.dimen.radius) + res.getDimension(R.dimen.line_width)).toInt())
            padding = (res.getDimension(R.dimen.frame_padding).toInt())
            textSize = (res.getDimension(R.dimen.frame_text_size).toInt())
            radius = (res.getDimension(R.dimen.radius).toInt())
            innerRadius = (res.getDimension(R.dimen.iner_radius).toInt())
        }

        frameTextPaint = Paint().apply {
            isAntiAlias = true
            textSize = chart.textSize.toFloat()
            color = res.getColor(R.color.gray_400)
        }
    }

}