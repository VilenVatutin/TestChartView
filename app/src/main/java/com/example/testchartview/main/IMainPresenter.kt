package com.example.testchartview.main

interface IMainPresenter {
    fun getData(ticker: String)
    fun injectView(view: IMainActivity)
}