package com.example.mylibrary.chart_view.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InputData(
    var graphValue: Double,
    var date: String = "",
) : Parcelable