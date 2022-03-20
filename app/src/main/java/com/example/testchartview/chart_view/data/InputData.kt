package com.example.chart.chart_view.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InputData(
    var graphValue: Int,
    var millis: Long = 0,
) : Parcelable