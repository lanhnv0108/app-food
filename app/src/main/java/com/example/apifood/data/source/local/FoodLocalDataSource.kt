package com.example.apifood.data.source.local

import com.example.apifood.data.source.FoodDataSource

class FoodLocalDataSource : FoodDataSource.Local{
    private object Holder{
        val INSTANCE = FoodLocalDataSource()
    }
    companion object{
        val instance : FoodLocalDataSource by lazy { Holder.INSTANCE }
    }
}