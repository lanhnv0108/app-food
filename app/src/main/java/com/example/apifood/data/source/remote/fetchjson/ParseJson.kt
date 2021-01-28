package com.example.apifood.data.source.remote.fetchjson

import com.example.apifood.data.model.Food
import com.example.apifood.data.model.FoodEntry
import org.json.JSONObject

class ParseJson {
    fun foodParseJson(jsonObject: JSONObject): Food {
        return Food(
            title = jsonObject.getString(FoodEntry.TITLE),
            description = jsonObject.getString(FoodEntry.DESCRIPTION),
            imageUrl = jsonObject.getString(FoodEntry.IMAGE_URL)
        )
    }
}