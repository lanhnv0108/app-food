package com.example.apifood.data.source.remote.fetchjson

import android.util.Log
import com.example.apifood.data.model.FoodEntry
import com.example.apifood.utils.Constant
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class ParseDataWithJson {
    fun getJonFromUrl(urlString: String?): String {
        val url = URL(urlString)
//        val url = URL("https://api.themoviedb.org/3/movie/upcoming?api_key=39902402e11902d8df5588a64ccf67c0&language=en-US")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.apply {
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            requestMethod = METHOD_GET
            setRequestProperty(Constant.BASE_NAME_KEY , Constant.BASE_VALUE_KEY)
            setRequestProperty("Cookie","fbcity=1; zl=en; fbtrack=c71b2c108e2ee2ba07778816a5b1ddf9; AWSALBTG=extjXjlO1qhtJqbLjhyguMfhFJVW9OyX//a9cQb9StXYhB4fkDX9jU+UVU+rpSDxiBxQdzxPp5ju1ez4zuteEQAEH7BHVFgJWk0/UUyd1RnAUZHVrrJUSGrzZTu6OVIWbPg2Sxsat1xm4914bRTqHxrJuz+3EEjPeRS0Akey3Cf2OmTOHXw=; AWSALBTGCORS=extjXjlO1qhtJqbLjhyguMfhFJVW9OyX//a9cQb9StXYhB4fkDX9jU+UVU+rpSDxiBxQdzxPp5ju1ez4zuteEQAEH7BHVFgJWk0/UUyd1RnAUZHVrrJUSGrzZTu6OVIWbPg2Sxsat1xm4914bRTqHxrJuz+3EEjPeRS0Akey3Cf2OmTOHXw=; csrf=24f3616e8e19611d958b227728045af3")
            doOutput = true
            doOutput = true
            connect()
            Log.e("TEST" , httpURLConnection.responseCode.toString())
        }
        val bufferedReader = BufferedReader(InputStreamReader(url.openStream()))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        Log.e("TEST", stringBuilder.toString())
        bufferedReader.close()
        httpURLConnection.disconnect()
        return stringBuilder.toString()
    }

    fun parseJsonToData(jsonObject: JSONObject?, keyEntity: String): Any? {
        val data = mutableListOf<Any>()
        Log.e("ARRAY", jsonObject?.getJSONArray("results").toString())
        try {
            val jsonArray = jsonObject?.getJSONArray(keyEntity)
            for (i in 0 until (jsonArray?.length() ?: 0)){
                val jsonObjects = jsonArray?.getJSONObject(i)
                val itemFood = ParseDataWithJson().parseJsonToObject(/*Get Object item i */jsonObjects?.getJSONObject(FoodEntry.COLLECTION) , keyEntity)
                itemFood?.let {
                    data.add(it)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()

        }
        return data

    }

    private fun parseJsonToObject(jsonObject: JSONObject?, keyEntity: String): Any? {
        try {
            jsonObject?.let {
                when (keyEntity) {
                    FoodEntry.COLLECTIONS -> return ParseJson().foodParseJson(it)
                    /**
                     * nother parse
                     */
                    else -> null
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    companion object {
        private const val TIME_OUT = 15000
        private const val METHOD_GET: String = "GET"
    }
}