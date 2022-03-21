package com.example.chart.chart_view.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DrawData(
    var startX: Float = 0f,
    var startY: Float = 0f,
    var stopX: Float = 0f,
    var stopY: Float = 0f
) : Parcelable
