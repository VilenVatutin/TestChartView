package com.example.testchartview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.chart.chart_view.ChartView
import com.example.chart.chart_view.data.InputData
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chart = findViewById<ChartView>(R.id.chart)
        chart.setData(createChartData())
    }

    private fun createChartData(): List<InputData> {
        val dataList: MutableList<InputData> = ArrayList<InputData>()
        dataList.add(InputData(10))
        dataList.add(InputData(25))
        dataList.add(InputData(20))
        dataList.add(InputData(30))
        dataList.add(InputData(20))
        dataList.add(InputData(40))
        dataList.add(InputData(10))
        dataList.add(InputData(20))
        dataList.add(InputData(30))
        dataList.add(InputData(40))
        dataList.add(InputData(10))
        dataList.add(InputData(25))
        dataList.add(InputData(30))
        dataList.add(InputData(20))
        dataList.add(InputData(40))
        dataList.add(InputData(10))
        dataList.add(InputData(25))
        dataList.add(InputData(20))
        dataList.add(InputData(30))
        dataList.add(InputData(20))
        dataList.add(InputData(50))
        dataList.add(InputData(40))
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