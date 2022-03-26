package com.example.mylibrary.chart_view

interface OnPointChosenLitener {
    fun onPointChosen(paddingLeft: Int, paddinTop: Int, info: String)
    fun hideDialog()
}