package com.example.testchartview.main

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.marginTop
import com.example.mylibrary.chart_view.data.InputData
import com.example.mylibrary.chart_view.ChartView
import com.example.testchartview.MyApplication
import com.example.testchartview.R
import javax.inject.Inject


class MainActivity : AppCompatActivity(), IMainActivity {
    @Inject
    lateinit var presenter: IMainPresenter

    lateinit var chart: ChartView
    lateinit var btn1: TextView
    lateinit var btn2: TextView
    lateinit var btn3: TextView
    lateinit var btn4: TextView
    private var currentButton = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApplication.appComponent.inject(this)
        chart = findViewById(R.id.chart)
        val rl = findViewById<RelativeLayout>(R.id.rl)
        val info = findViewById<TextView>(R.id.info)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn1.setOnClickListener {
            currentButton = 1
            onPointCLick()
        }
        btn2.setOnClickListener {
            currentButton = 2
            onPointCLick()
        }
        btn3.setOnClickListener {
            currentButton = 3
            onPointCLick()
        }
        btn4.setOnClickListener {
            currentButton = 4
            onPointCLick()
        }
        chart.setOnPointChosenListener(object :
            com.example.mylibrary.chart_view.OnPointChosenLitener {
            override fun onPointChosen(paddingLeft: Int, paddinTop: Int, sInfo: String) {
                val params = RelativeLayout.LayoutParams(rl.layoutParams)
                params.leftMargin = paddingLeft - info.width / 2
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

    override fun onResume() {
        super.onResume()
        if (currentButton == 0) {
            currentButton = 1
            onPointCLick()
        }
        setButtonState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CURRENT_BUTTON, currentButton)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        currentButton = savedInstanceState.getInt(CURRENT_BUTTON)
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun onPointCLick() {
        when (currentButton) {
            1 -> {
                chart.apply {
                    setLineColor(resources.getColor(R.color.blue))
                    setLineWidth(resources.getDimension(R.dimen.line_width))
                }
                presenter.getData("ethereum")
                setButtonState()

            }
            2 -> {
                chart.apply {
                    setLineColor(resources.getColor(R.color.purple_200))
                    setLineWidth(resources.getDimension(R.dimen.line_width))
                }
                presenter.getData("bitcoin")
                setButtonState()
            }
            3 -> {
                chart.apply {
                    setLineColor(resources.getColor(R.color.teal_200))
                    setLineWidth(resources.getDimension(R.dimen.line_width))
                }
                presenter.getData("litecoin")
                setButtonState()
            }
            4 -> {
                chart.apply {
                    setLineColor(resources.getColor(R.color.gray_900))
                    setLineWidth(resources.getDimension(R.dimen.line_width))
                }
                presenter.getData("cardano")
                setButtonState()
            }
        }
    }

    private fun setButtonState() {
        when (currentButton) {
            1 -> {
                btn1.background = AppCompatResources.getDrawable(this, R.drawable.button)
                btn2.setBackgroundColor(resources.getColor(R.color.white))
                btn3.setBackgroundColor(resources.getColor(R.color.white))
                btn4.setBackgroundColor(resources.getColor(R.color.white))

            }
            2 -> {
                btn1.setBackgroundColor(resources.getColor(R.color.white))
                btn2.background = AppCompatResources.getDrawable(this, R.drawable.button)
                btn3.setBackgroundColor(resources.getColor(R.color.white))
                btn4.setBackgroundColor(resources.getColor(R.color.white))
            }
            3 -> {
                btn1.setBackgroundColor(resources.getColor(R.color.white))
                btn2.setBackgroundColor(resources.getColor(R.color.white))
                btn3.background = AppCompatResources.getDrawable(this, R.drawable.button)
                btn4.setBackgroundColor(resources.getColor(R.color.white))
            }
            4 -> {
                btn1.setBackgroundColor(resources.getColor(R.color.white))
                btn2.setBackgroundColor(resources.getColor(R.color.white))
                btn3.setBackgroundColor(resources.getColor(R.color.white))
                btn4.background = AppCompatResources.getDrawable(this, R.drawable.button)
            }
        }
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

    override fun showData(prices: List<InputData>) {
        chart.setData(prices)
    }

    companion object {
        const val CURRENT_BUTTON = "current button"
    }
}