package com.example.testchartview

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import com.example.chart.chart_view.ChartView
import com.example.chart.chart_view.data.InputData
import com.example.testchartview.chart_view.OnPointChosenLitener
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chart = findViewById<ChartView>(R.id.chart)
        val rl = findViewById<RelativeLayout>(R.id.rl)
        val info = findViewById<TextView>(R.id.info)
        chart.setData(createChartData())

        chart.setOnPointChosenListener(object : OnPointChosenLitener{
            override fun onPointChosen(paddingLeft: Int, paddinTop: Int, sInfo: String) {
                val params = RelativeLayout.LayoutParams(rl.layoutParams)
                params.leftMargin = paddingLeft -info.width/2
                params.topMargin = paddinTop - info.height - getPx(10) + chart.marginTop
                rl.layoutParams = params
                rl.visibility = View.VISIBLE
                info.text = sInfo
//                rl.addView(iv, params)
            }


            override fun hideDialog() {
                rl.visibility = View.GONE
            }
//
//            override fun onPointChosen(paddingLeft: Float, paddinTop: Float) {
//                val params = RelativeLayout.LayoutParams(20, 20)
//                params.leftMargin = paddingLeft.toInt()
//                params.topMargin = paddinTop.toInt()
//                rl.layoutParams = params
//                rl.visibility = View.VISIBLE
//            }
        })
    }
fun getPx(dp: Int): Int{
    var r: Resources = resources
    return  TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        r.getDisplayMetrics()
    ).toInt()
}


    private fun createChartData(): List<InputData> {
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