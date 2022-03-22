package com.example.chart.chart_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.content.res.AppCompatResources
import com.example.chart.chart_view.data.Chart
import com.example.chart.chart_view.data.DrawData
import com.example.chart.chart_view.data.InputData
import com.example.testchartview.R
import com.example.testchartview.chart_view.data.PointData

class DrawController(
    val context: Context,
    val chart: Chart
) {

    private var frameTextPaint: Paint

    fun draw(canvas: Canvas) {
        for (i in 0 until Chart.MAX_ITEMS_COUNT) {
            drawChart(canvas, i)
        }
        if (chart.isFrameNeeded) drawFrame(canvas)
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
            var titleY = currHeight
            if (i <= 0) {
                titleY = height
            } else if (textSize + chart.heightOffset > currHeight) {
                titleY = currHeight + textSize
            }
            chart.frameLinePaint.color
            canvas.drawLine(
                0f, currHeight, width, currHeight,
                chart.frameLinePaint
            )
            val title = "\$" + String.format("%.2f", currTitle)
            canvas.drawText(title, padding.toFloat(), titleY, frameTextPaint!!)
            currHeight -= chartPartHeight
            currTitle += ((correctedMaxValue - correctedMinValue) / Chart.CHART_PARTS)
        }
    }

    private fun drawChart(canvas: Canvas, position: Int) {
        val dataList: List<DrawData> = chart.drawData
        if (position > dataList.lastIndex) {
            return
        }
        val drawData = dataList[position]
        val startX: Float = drawData.startX
        val startY: Float = drawData.startY
        val stopX: Float = drawData.stopX
        val stopY: Float = drawData.stopY
        val alpha = 255
        chart.points[position] = PointData(
            startX,
            startY,
            "\$${chart.showingData[position].graphValue} \n" + chart.showingData[position].date
        )
        drawChart(canvas, startX, startY, stopX, stopY, alpha, position)
    }

    private fun drawChart(
        canvas: Canvas,
        startX: Float,
        startY: Float,
        stopX: Float,
        stopY: Float,
        alpha: Int,
        position: Int
    ) {
        val radius: Int = chart.radius
        val inerRadius: Int = chart.innerRadius
        canvas.drawLine(
            startX, startY, stopX, stopY,
            chart.linePaint
        )
        if (position > 0) {
            chart.pointDrawable?.let{
                val d = AppCompatResources.getDrawable(context, it)
                d?.setBounds(startX.toInt() - d.intrinsicWidth/2, startY.toInt() - d.intrinsicHeight/2, startX.toInt() + d.intrinsicWidth/2, startY.toInt() + d.intrinsicHeight/2)
                d?.draw(canvas)
            }

//            chart.linePaint.alpha = alpha
//            canvas.drawCircle(startX, startY, radius.toFloat(), chart.linePaint)
//            canvas.drawCircle(startX, startY, inerRadius.toFloat(), chart.linePaint)
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