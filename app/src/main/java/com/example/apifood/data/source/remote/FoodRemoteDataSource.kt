package com.example.apifood.data.source.remote

import android.util.Log
import com.example.apifood.data.model.Food
import com.example.apifood.data.model.FoodEntry
import com.example.apifood.data.source.FoodDataSource
import com.example.apifood.data.source.remote.fetchjson.GetJsonFromUrl
import com.example.apifood.utils.Constant

@Suppress("DEPRECATION")
class FoodRemoteDataSource : FoodDataSource.Remote {
    private val baseUrl = Constant.BASE_URL + Constant.BASE_CITY

    /**
     * Get from remote
     */
    override fun getFood(listener: OnFetchDataListener<MutableList<Food>>) {
        GetJsonFromUrl(listener,FoodEntry.COLLECTIONS).execute(baseUrl)
        Log.e("TEST" , "URL")
    }

    private object Holder {
        val INSTANCE = FoodRemoteDataSource()
    }

    companion object {
        val instance : FoodRemoteDataSource by lazy { Holder.INSTANCE }
    }

}