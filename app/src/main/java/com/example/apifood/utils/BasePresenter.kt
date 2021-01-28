package com.example.apifood.utils

import android.view.View

interface BasePresenter<T> {
    fun onStart()

    fun onStop()

    fun setView(view: T?)
}