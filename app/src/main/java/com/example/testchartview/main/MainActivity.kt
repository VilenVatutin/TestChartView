package com.example.testchartview.main

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import com.example.testchartview.chart_view.ChartView
import com.example.chart.chart_view.data.InputData
import com.example.testchartview.MyApplication
import com.example.testchartview.R
import com.example.testchartview.chart_view.OnPointChosenLitener
import javax.inject.Inject


class MainActivity : AppCompatActivity(), IMainActivity {
    @Inject
    lateinit var presenter: IMainPresenter

    lateinit var chart: ChartView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as? MyApplication)?.appComponent?.inject(this)
        chart = findViewById(R.id.chart)
        val rl = findViewById<RelativeLayout>(R.id.rl)
        val info = findViewById<TextView>(R.id.info)
        val btn1 = findViewById<TextView>(R.id.btn1)
        val btn2 = findViewById<TextView>(R.id.btn2)
        val btn3 = findViewById<TextView>(R.id.btn3)
        val btn4 = findViewById<TextView>(R.id.btn4)
        btn1.setOnClickListener {
            chart.apply {
                setLineColor(resources.getColor(R.color.blue))
                setLineWidth(resources.getDimension(R.dimen.line_width))
            }
//            chart.setData(creat2ChartData())
            presenter.getData("0-5x-long-ethereum-classic-token")
        }
        btn2.setOnClickListener {
            chart.apply {
                setLineColor(resources.getColor(R.color.purple_200))
                setLineWidth(resources.getDimension(R.dimen.line_width))
            }
            presenter.getData("0-5x-long-bitcoin-cash-token")
        }
        btn3.setOnClickListener {
            chart.apply {
                setLineColor(resources.getColor(R.color.teal_200))
                setLineWidth(resources.getDimension(R.dimen.line_width))
            }
            presenter.getData("0-5x-long-dogecoin-token")
        }
        btn4.setOnClickListener {
            chart.apply {
                setLineColor(resources.getColor(R.color.gray_900))
                setLineWidth(resources.getDimension(R.dimen.line_width))
            }
            presenter.getData("0-5x-long-altcoin-index-token")
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

        presenter.injectView(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        chart.removeOnPointChosenListener()
    }

    fun getPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    private fun create1ChartData(): List<InputData> {
        val dataList: MutableList<InputData> = ArrayList<InputData>()
        dataList.add(InputData(1314.99, "07.06.2012"))
        dataList.add(InputData(1325.66, "08.06.2012"))
        dataList.add(InputData(1308.93, "11.06.2012"))
        dataList.add(InputData(1324.18, "12.06.2012"))
        dataList.add(InputData(1314.88, "13.06.2012"))
        dataList.add(InputData(132.1,"14.06.2012"))
        dataList.add(InputData(1342.84, "15.06.2012"))
        dataList.add(InputData(1344.78, "18.06.2012"))
        dataList.add(InputData(1357.98, "19.06.2012"))
        dataList.add(InputData(1355.69, "20.06.2012"))
        dataList.add(InputData(1325.51, "21.06.2012"))
        dataList.add(InputData(1335.02, "22.06.2012"))
        dataList.add(InputData(1313.72, "25.06.2012"))
        dataList.add(InputData(1319.99, "26.06.2012"))
        dataList.add(InputData(1331.85, "27.06.2012"))
        dataList.add(InputData(1329.04, "28.06.2012"))
        dataList.add(InputData(1362.16, "29.06.2012"))
        dataList.add(InputData(1365.51, "02.07.2012"))
        dataList.add(InputData(1374.02, "03.07.2012"))
        dataList.add(InputData(1367.58, "05.07.2012"))
        dataList.add(InputData(1354.68, "06.07.2012"))
        dataList.add(InputData(1352.46, "09.07.2012"))
        dataList.add(InputData(1341.47, "10.07.2012"))
        dataList.add(InputData(1341.45, "11.07.2012"))
        dataList.add(InputData(1334.76, "12.07.2012"))
        dataList.add(InputData(1356.78, "13.07.2012"))
        dataList.add(InputData(1353.64, "16.07.2012"))
        dataList.add(InputData(1363.67, "17.07.2012"))
        dataList.add(InputData(1372.78, "18.07.2012"))
        dataList.add(InputData(1376.51, "19.07.2012"))
        dataList.add(InputData(1362.66, "20.07.2012"))
        dataList.add(InputData(1350.52, "23.07.2012"))
        dataList.add(InputData(1338.31, "24.07.2012"))
        dataList.add(InputData(1337.89, "25.07.2012"))
        dataList.add(InputData(1360.02, "26.07.2012"))
        dataList.add(InputData(1385.97, "27.07.2012"))
        dataList.add(InputData(138.3,"30.07.2012"))
        dataList.add(InputData(1379.3, "31.07.2012"))
        dataList.add(InputData(1375.14, "01.08.2012"))
        dataList.add(InputData(.365,"02.08.2012"))
        dataList.add(InputData(1390.99, "03.08.2012"))
        dataList.add(InputData(1394.2, "06.08.2012"))
        dataList.add(InputData(1401.35, "07.08.2012"))
        dataList.add(InputData(1402.22, "08.08.2012"))
        dataList.add(InputData(140.8,"09.08.2012"))
        dataList.add(InputData(1405.87, "10.08.2012"))
        dataList.add(InputData(1404.11, "13.08.2012"))
        dataList.add(InputData(1403.93, "14.08.2012"))
        dataList.add(InputData(1405.53, "15.08.2012"))
        dataList.add(InputData(1415.51, "16.08.2012"))
        dataList.add(InputData(1418.16, "17.08.2012"))
        dataList.add(InputData(1418.13, "20.08.2012"))
        dataList.add(InputData(1413.17, "21.08.2012"))
        dataList.add(InputData(1413.49, "22.08.2012"))
        dataList.add(InputData(1402.08, "23.08.2012"))
        dataList.add(InputData(1411.13, "24.08.2012"))
        dataList.add(InputData(1410.44, "27.08.2012"))
        dataList.add(InputData(140.03,"28.08.2012"))
        dataList.add(InputData(1410.49, "29.08.2012"))
        dataList.add(InputData(1399.48, "30.08.2012"))
        dataList.add(InputData(1406.58, "31.08.2012"))
        dataList.add(InputData(1404.94, "04.09.2012"))
        dataList.add(InputData(1403.44, "05.09.2012"))
        dataList.add(InputData(143.12, "06.09.201"))
        return dataList
    }
    private fun creat2ChartData(): List<InputData> {
        val dataList: MutableList<InputData> = ArrayList<InputData>()
        dataList.add(InputData(1407.05, "04.12.2012"))
        dataList.add(InputData(1409.28, "05.12.2012"))
        dataList.add(InputData(1413.94, "06.12.2012"))
        dataList.add(InputData(1418.07, "07.12.2012"))
        dataList.add(InputData(1418.55, "10.12.2012"))
        dataList.add(InputData(1427.84, "11.12.2012"))
        dataList.add(InputData(1428.48, "12.12.2012"))
        dataList.add(InputData(1419.45, "13.12.2012"))
        dataList.add(InputData(1413.58, "14.12.2012"))
        dataList.add(InputData(1430.36, "17.12.2012"))
        dataList.add(InputData(1446.79, "18.12.2012"))
        dataList.add(InputData(1435.81, "19.12.2012"))
        dataList.add(InputData(1443.69, "20.12.2012"))
        dataList.add(InputData(1430.15, "21.12.2012"))
        dataList.add(InputData(1426.66, "24.12.2012"))
        dataList.add(InputData(1419.83, "26.12.2012"))
        dataList.add(InputData(1418.1,  "27.12.2012"))
        dataList.add(InputData(1402.43, "28.12.2012"))
        dataList.add(InputData(1426.19, "31.12.2012"))
        dataList.add(InputData(1462.42, "02.01.2013"))
        dataList.add(InputData(1459.37, "03.01.2013"))
        dataList.add(InputData(1466.47, "04.01.2013"))
        dataList.add(InputData(1461.89, "07.01.2013"))
        dataList.add(InputData(1457.15, "08.01.2013"))
        dataList.add(InputData(1461.02, "09.01.2013"))
        dataList.add(InputData(1472.12, "10.01.2013"))
        dataList.add(InputData(1472.05, "11.01.2013"))
        dataList.add(InputData(1470.68, "14.01.2013"))
        dataList.add(InputData(1472.34, "15.01.2013"))
        dataList.add(InputData(1472.63, "16.01.2013"))
        dataList.add(InputData(1480.94, "17.01.2013"))
        dataList.add(InputData(1485.98, "18.01.2013"))
        dataList.add(InputData(1492.56, "22.01.2013"))
        dataList.add(InputData(1494.81, "23.01.2013"))
        dataList.add(InputData(1494.82, "24.01.2013"))
        dataList.add(InputData(1502.96, "25.01.2013"))
        dataList.add(InputData(1500.18, "28.01.2013"))
        dataList.add(InputData(1507.84, "29.01.2013"))
        dataList.add(InputData(1501.96, "30.01.2013"))
        dataList.add(InputData(1498.11, "31.01.2013"))
        dataList.add(InputData(1513.17, "01.02.2013"))
        dataList.add(InputData(1495.71, "04.02.2013"))
        dataList.add(InputData(1511.29, "05.02.2013"))
        dataList.add(InputData(1512.12, "06.02.2013"))
        dataList.add(InputData(1509.39, "07.02.2013"))
        dataList.add(InputData(1517.93, "08.02.2013"))
        dataList.add(InputData(1517.01, "11.02.2013"))
        dataList.add(InputData(1519.43, "12.02.2013"))
        dataList.add(InputData(1520.33, "13.02.2013"))
        dataList.add(InputData(1521.38, "14.02.2013"))
        dataList.add(InputData(1519.79, "15.02.2013"))
        dataList.add(InputData(1530.94, "19.02.2013"))
        dataList.add(InputData(1511.95, "20.02.2013"))
        dataList.add(InputData(1502.42, "21.02.2013"))
        dataList.add(InputData(1515.6,  "22.02.2013"))
        dataList.add(InputData(1487.85, "25.02.2013"))
        dataList.add(InputData(1496.94, "26.02.2013"))
        dataList.add(InputData(1515.99, "27.02.2013"))
        dataList.add(InputData(1514.68, "28.02.2013"))
        dataList.add(InputData(1518.2,  "01.03.2013"))
        dataList.add(InputData(1525.2,  "04.03.2013"))
        dataList.add(InputData(1539.79, "05.03.2013"))
        dataList.add(InputData(1541.46, "06.03.2013"))
        dataList.add(InputData(1544.26, "07.03.2013"))
        return dataList
    }
    private fun creat3ChartData(): List<InputData> {
        val dataList: MutableList<InputData> = ArrayList<InputData>()
        dataList.add(InputData(1597.57, "30.04.2013"))
        dataList.add(InputData(1582.7,  "01.05.2013"))
        dataList.add(InputData(1597.59, "02.05.2013"))
        dataList.add(InputData(1614.42, "03.05.2013"))
        dataList.add(InputData(1617.5,  "06.05.2013"))
        dataList.add(InputData(1625.96, "07.05.2013"))
        dataList.add(InputData(1632.69, "08.05.2013"))
        dataList.add(InputData(1626.67, "09.05.2013"))
        dataList.add(InputData(1633.7,  "10.05.2013"))
        dataList.add(InputData(1633.77, "13.05.2013"))
        dataList.add(InputData(1650.34, "14.05.2013"))
        dataList.add(InputData(1658.78, "15.05.2013"))
        dataList.add(InputData(1650.47, "16.05.2013"))
        dataList.add(InputData(1667.47, "17.05.2013"))
        dataList.add(InputData(1666.29, "20.05.2013"))
        dataList.add(InputData(1669.16, "21.05.2013"))
        dataList.add(InputData(1655.35, "22.05.2013"))
        dataList.add(InputData(1650.51, "23.05.2013"))
        dataList.add(InputData(1649.6,  "24.05.2013"))
        dataList.add(InputData(1660.06, "28.05.2013"))
        dataList.add(InputData(1648.36, "29.05.2013"))
        dataList.add(InputData(1654.41, "30.05.2013"))
        dataList.add(InputData(1630.74, "31.05.2013"))
        dataList.add(InputData(1640.42, "03.06.2013"))
        dataList.add(InputData(1631.38, "04.06.2013"))
        dataList.add(InputData(1608.9,  "05.06.2013"))
        dataList.add(InputData(1622.56, "06.06.2013"))
        dataList.add(InputData(1643.38, "07.06.2013"))
        dataList.add(InputData(1642.81, "10.06.2013"))
        dataList.add(InputData(1626.13, "11.06.2013"))
        dataList.add(InputData(1612.52, "12.06.2013"))
        dataList.add(InputData(1636.36, "13.06.2013"))
        dataList.add(InputData(1626.73, "14.06.2013"))
        dataList.add(InputData(1639.04, "17.06.2013"))
        dataList.add(InputData(1651.81, "18.06.2013"))
        dataList.add(InputData(1628.93, "19.06.2013"))
        dataList.add(InputData(1588.19, "20.06.2013"))
        dataList.add(InputData(1592.43, "21.06.2013"))
        dataList.add(InputData(1573.09, "24.06.2013"))
        dataList.add(InputData(1588.03, "25.06.2013"))
        dataList.add(InputData(1603.26, "26.06.2013"))
        dataList.add(InputData(1613.2,  "27.06.2013"))
        dataList.add(InputData(1606.28, "28.06.2013"))
        dataList.add(InputData(1614.96, "01.07.2013"))
        dataList.add(InputData(1614.08, "02.07.2013"))
        dataList.add(InputData(1615.41, "03.07.2013"))
        dataList.add(InputData(1631.89, "05.07.2013"))
        dataList.add(InputData(1640.46, "08.07.2013"))
        dataList.add(InputData(1652.32, "09.07.2013"))
        dataList.add(InputData(1652.62, "10.07.2013"))
        dataList.add(InputData(1675.02, "11.07.2013"))
        dataList.add(InputData(1680.19, "12.07.2013"))
        dataList.add(InputData(1682.5,  "15.07.2013"))
        dataList.add(InputData(1676.26, "16.07.2013"))
        dataList.add(InputData(1680.91, "17.07.2013"))
        dataList.add(InputData(1689.37, "18.07.2013"))
        dataList.add(InputData(1692.09, "19.07.2013"))
        dataList.add(InputData(1695.53, "22.07.2013"))
        dataList.add(InputData(1692.39, "23.07.2013"))
        dataList.add(InputData(1685.94, "24.07.2013"))
        dataList.add(InputData(1690.25, "25.07.2013"))
        dataList.add(InputData(1691.65, "26.07.2013"))
        dataList.add(InputData(1685.33, "29.07.2013"))
        dataList.add(InputData(1685.96, "30.07.2013"))
        return dataList
    }
    private fun creat4ChartData(): List<InputData> {
        val dataList: MutableList<InputData> = ArrayList<InputData>()
        dataList.add(InputData(1365.68, "29.02.2012"))
        dataList.add(InputData(1374.09, "01.03.2012"))
        dataList.add(InputData(1369.63, "02.03.2012"))
        dataList.add(InputData(1364.33, "05.03.2012"))
        dataList.add(InputData(1343.36, "06.03.2012"))
        dataList.add(InputData(1352.63, "07.03.2012"))
        dataList.add(InputData(1365.91, "08.03.2012"))
        dataList.add(InputData(1370.87, "09.03.2012"))
        dataList.add(InputData(1371.09, "12.03.2012"))
        dataList.add(InputData(1395.95, "13.03.2012"))
        dataList.add(InputData(1394.28, "14.03.2012"))
        dataList.add(InputData(1402.6, "15.03.2012"))
        dataList.add(InputData(1404.17, "16.03.2012"))
        dataList.add(InputData(1409.75, "19.03.2012"))
        dataList.add(InputData(1405.52, "20.03.2012"))
        dataList.add(InputData(1402.89, "21.03.2012"))
        dataList.add(InputData(1392.78, "22.03.2012"))
        dataList.add(InputData(1397.11, "23.03.2012"))
        dataList.add(InputData(1416.51, "26.03.2012"))
        dataList.add(InputData(1412.52, "27.03.2012"))
        dataList.add(InputData(1405.54, "28.03.2012"))
        dataList.add(InputData(1403.28, "29.03.2012"))
        dataList.add(InputData(1408.47, "30.03.2012"))
        dataList.add(InputData(1419.04, "02.04.2012"))
        dataList.add(InputData(1413.38, "03.04.2012"))
        dataList.add(InputData(1398.96, "04.04.2012"))
        dataList.add(InputData(1398.08, "05.04.2012"))
        dataList.add(InputData(1382.2, "09.04.2012"))
        dataList.add(InputData(1358.59, "10.04.2012"))
        dataList.add(InputData(1368.71, "11.04.2012"))
        dataList.add(InputData(1387.57, "12.04.2012"))
        dataList.add(InputData(1370.26, "13.04.2012"))
        dataList.add(InputData(1369.57, "16.04.2012"))
        dataList.add(InputData(1390.78, "17.04.2012"))
        dataList.add(InputData(1385.14, "18.04.2012"))
        dataList.add(InputData(1376.92, "19.04.2012"))
        dataList.add(InputData(1378.53, "20.04.2012"))
        dataList.add(InputData(1366.94, "23.04.2012"))
        dataList.add(InputData(1371.97, "24.04.2012"))
        dataList.add(InputData(1390.69, "25.04.2012"))
        dataList.add(InputData(1399.98, "26.04.2012"))
        dataList.add(InputData(1403.36, "27.04.2012"))
        dataList.add(InputData(1397.91, "30.04.2012"))
        dataList.add(InputData(1405.82, "01.05.2012"))
        dataList.add(InputData(1402.31, "02.05.2012"))
        dataList.add(InputData(1391.57, "03.05.2012"))
        dataList.add(InputData(1369.1, "04.05.2012"))
        dataList.add(InputData(1369.58, "07.05.2012"))
        dataList.add(InputData(1363.72, "08.05.2012"))
        dataList.add(InputData(1354.58, "09.05.2012"))
        dataList.add(InputData(1357.99, "10.05.2012"))
        dataList.add(InputData(1353.39, "11.05.2012"))
        dataList.add(InputData(1338.35, "14.05.2012"))
        dataList.add(InputData(1330.66, "15.05.2012"))
        dataList.add(InputData(1324.8, "16.05.2012"))
        dataList.add(InputData(1304.86, "17.05.2012"))
        dataList.add(InputData(1295.22, "18.05.2012"))
        dataList.add(InputData(1315.99, "21.05.2012"))
        dataList.add(InputData(1316.63, "22.05.2012"))
        dataList.add(InputData(1318.86, "23.05.2012"))
        dataList.add(InputData(1320.68, "24.05.2012"))
        dataList.add(InputData(1317.82, "25.05.2012"))
        dataList.add(InputData(1332.42, "29.05.2012"))
        dataList.add(InputData(1313.32, "30.05.2012"))
        dataList.add(InputData(1310.33, "31.05.2012"))
        dataList.add(InputData(1278.04, "01.06.2012"))
        dataList.add(InputData(1278.18, "04.06.2012"))
        dataList.add(InputData(1285.5, "05.06.2012"))
        dataList.add(InputData(1315.13, "06.06.201"))
        return dataList
    }

    override fun showData(prices: List<InputData>) {
        chart.setData(prices)
    }
}