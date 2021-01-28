package com.example.apifood.data.source.repository

import com.example.apifood.data.model.Food
import com.example.apifood.data.source.FoodDataSource
import com.example.apifood.data.source.local.FoodLocalDataSource
import com.example.apifood.data.source.remote.FoodRemoteDataSource
import com.example.apifood.data.source.remote.OnFetchDataListener

class FoodRepository private constructor(
    private val remote: FoodDataSource.Remote,
    private val local: FoodDataSource.Local
) {
    /**
     * get data from Remote
     */
    fun getFood(listener: OnFetchDataListener<MutableList<Food>>) {
        remote.getFood(listener)
    }

    private object Holder {
        val INSTANCE = FoodRepository(
            remote = FoodRemoteDataSource.instance,
            local = FoodLocalDataSource.instance
        )
    }

    companion object {
        val instance : FoodRepository by lazy { Holder.INSTANCE }
    }
}