package com.example.apifood.data.model

data class Food(
    val title: String = "",
    val description: String = "",
    val imageUrl: String =""
)

object FoodEntry {
    const val COLLECTIONS = "collections"
    const val COLLECTION = "collection"
    const val TITLE = "title"
    const val DESCRIPTION = "description"
    const val IMAGE_URL = "image_url"
}