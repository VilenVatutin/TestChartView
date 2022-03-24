package com.example.testchartview.chart_view

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.PointF
import com.example.chart.chart_view.data.DrawData
import com.example.chart.chart_view.data.InputData

class Bezie {

    var conPoint1 = mutableListOf<PointF>()
    var conPoint2 = mutableListOf<PointF>()
    val path = Path()

    fun getBeziePath(data: List<DrawData>): Path{
        conPoint1 = mutableListOf()
        conPoint2 = mutableListOf()
        calculateConnectionPointsForBezierCurve(data)
        return drawBezierCurve(data)
    }


    private fun calculateConnectionPointsForBezierCurve(data: List<DrawData>) {
        for (i in data.indices) {
            conPoint1.add(PointF((data[i].stopX + data[i].startX) / 2, data[i].startY))
            conPoint2.add(PointF((data[i].stopX + data[i].startX) / 2, data[i].stopY))
        }
    }

    private fun drawBezierCurve(data: List<DrawData>): Path {
        if (data.isNotEmpty() && conPoint1.isNotEmpty() && conPoint2.isNotEmpty()){
            path.reset()
            path.moveTo(data.first().startX, data.first().startY)
            for (i in 1 .. data.lastIndex) {
                path.cubicTo(
                    conPoint1[i - 1].x, conPoint1[i - 1].y, conPoint2[i - 1].x, conPoint2[i - 1].y,
                    data[i].startX, data[i].startY
                )
            }
        }
        return path
    }

}