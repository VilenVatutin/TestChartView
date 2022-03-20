package com.example.testchartview

import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.graphics.Paint
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import com.example.testchartview.chart_view.ChartView
import com.example.chart.chart_view.data.InputData
import com.example.testchartview.chart_view.OnPointChosenLitener
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    lateinit var chart: ChartView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chart = findViewById(R.id.chart)
        val rl = findViewById<RelativeLayout>(R.id.rl)
        val info = findViewById<TextView>(R.id.info)
        val btnBlue = findViewById<View>(R.id.btnBlue)
        val btnPurple = findViewById<View>(R.id.btnPurple)
        val btnHideFrame = findViewById<TextView>(R.id.btnHideFrame)
        val btnShowFrame = findViewById<TextView>(R.id.btnShowFrame)
        val btnRotate = findViewById<TextView>(R.id.btnRotate)
        btnHideFrame.setOnClickListener {
            chart.hideFrame()
        }

        btnShowFrame.setOnClickListener {
            chart.showFrame()
        }
        btnRotate.setOnClickListener {
            requestedOrientation =
                if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        btnBlue.setOnClickListener {
            chart.setLineColor(resources.getColor(R.color.blue))
//            chart.setLineWidth(resources.getDimension(R.dimen.line_width))
            chart.setData(createBlueChartData())
        }
        btnPurple.setOnClickListener {
            chart.setLineColor(resources.getColor(R.color.purple_200))
//            chart.setLineWidth(resources.getDimension(R.dimen.line_width))
            chart.setData(createPurpleChartData())
        }

        chart.setOnPointChosenListener(object : OnPointChosenLitener {
            override fun onPointChosen(paddingLeft: Int, paddinTop: Int, sInfo: String) {
                val params = RelativeLayout.LayoutParams(rl.layoutParams)
                params.leftMargin = paddingLeft - info.width / 2 + getPx(8)
                params.topMargin = paddinTop - info.height - getPx(10) + chart.marginTop
                rl.layoutParams = params
                rl.visibility = View.VISIBLE
                info.text = sInfo
            }

            override fun hideDialog() {
                rl.visibility = View.GONE
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        chart.removeOnPointChosenListener()
    }

    fun getPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.getDisplayMetrics()
        ).toInt()
    }

    private fun createPurpleChartData(): List<InputData> {
        val dataList: MutableList<InputData> = ArrayList<InputData>()
        dataList.add(InputData(70))
        dataList.add(InputData(83))
        dataList.add(InputData(94))
        dataList.add(InputData(121))
        dataList.add(InputData(60))
        dataList.add(InputData(78))
        dataList.add(InputData(80))
        dataList.add(InputData(50))
        dataList.add(InputData(100))
        dataList.add(InputData(110))
        dataList.add(InputData(120))
        dataList.add(InputData(110))
        dataList.add(InputData(100))
        dataList.add(InputData(90))
        dataList.add(InputData(80))
        dataList.add(InputData(70))
        dataList.add(InputData(60))
        dataList.add(InputData(50))
        dataList.add(InputData(67))
        dataList.add(InputData(67))
        dataList.add(InputData(89))
        dataList.add(InputData(100))
        dataList.add(InputData(212))
        dataList.add(InputData(114))
        dataList.add(InputData(243))
        dataList.add(InputData(175))
        dataList.add(InputData(156))
        dataList.add(InputData(7))
        dataList.add(InputData(9))
        dataList.add(InputData(9))
        dataList.add(InputData(9))
        dataList.add(InputData(9))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(23))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(61))
        dataList.add(InputData(55))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(20))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(200))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(124))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(5))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(20))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        var currMillis = System.currentTimeMillis()
        currMillis -= currMillis % TimeUnit.DAYS.toMillis(1)
        for (i in dataList.indices) {
            val position = (dataList.size - 1 - i).toLong()
            val offsetMillis = TimeUnit.DAYS.toMillis(position)
            val millis = currMillis - offsetMillis
            dataList[i].millis = millis
        }
        return dataList
    }

    private fun createBlueChartData(): List<InputData> {
        val dataList: MutableList<InputData> = ArrayList<InputData>()
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(5))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(8))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(5))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(9))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(20))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(200))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(5))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(20))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(5))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(20))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(5))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(20))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(5))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(20))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(5))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(20))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        dataList.add(InputData(1))
        dataList.add(InputData(2))
        dataList.add(InputData(3))
        dataList.add(InputData(4))
        dataList.add(InputData(5))
        dataList.add(InputData(6))
        dataList.add(InputData(7))
        dataList.add(InputData(20))
        dataList.add(InputData(9))
        dataList.add(InputData(10))
        dataList.add(InputData(11))
        dataList.add(InputData(12))
        dataList.add(InputData(11))
        dataList.add(InputData(10))
        dataList.add(InputData(9))
        dataList.add(InputData(8))
        dataList.add(InputData(7))
        dataList.add(InputData(6))
        dataList.add(InputData(5))
        dataList.add(InputData(4))
        dataList.add(InputData(3))
        dataList.add(InputData(2))
        var currMillis = System.currentTimeMillis()
        currMillis -= currMillis % TimeUnit.DAYS.toMillis(1)
        for (i in dataList.indices) {
            val position = (dataList.size - 1 - i).toLong()
            val offsetMillis = TimeUnit.DAYS.toMillis(position)
            val millis = currMillis - offsetMillis
            dataList[i].millis = millis
        }
        return dataList
    }

}