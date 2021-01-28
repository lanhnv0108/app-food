package com.example.apifood.utils

interface BasePresenter<T> {
    fun onStart()

    fun onStop()

    fun setView(view: T?)
}