package com.example.mylibrary.chart_view

interface OnPointChosenLitener {// listener для того чтобы слушать на какой точке находится палец
    fun onPointChosen(paddingLeft: Int, paddinTop: Int, info: String)
    fun hideDialog()
}