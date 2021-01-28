package com.example.apifood.data.source.remote.fetchjson

import com.example.apifood.data.model.FoodEntry
import com.example.apifood.utils.Constant
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ParseDataWithJson {
    fun getJonFromUrl(urlString: String?): String {
        val url = URL(urlString)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.apply {
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            requestMethod = METHOD_GET
            setRequestProperty(Constant.BASE_NAME_KEY, Constant.BASE_VALUE_KEY)
            doOutput = true
            connect()
        }
        val statusCode = httpURLConnection.responseCode
        if (statusCode == 200) {
            val bufferedInputStream =
                BufferedInputStream(httpURLConnection.inputStream) as InputStream
            val bufferedReader = BufferedReader(InputStreamReader(bufferedInputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            bufferedInputStream.close()
            bufferedReader.close()
            httpURLConnection.disconnect()
            return stringBuilder.toString()
        }
        return ""
    }

    fun parseJsonToData(jsonObject: JSONObject?, keyEntity: String): Any {
        val data = mutableListOf<Any>()
        try {
            val jsonArray = jsonObject?.getJSONArray(keyEntity)
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val jsonObjects = jsonArray?.getJSONObject(i)
                val itemFood =
                    ParseDataWithJson().parseJsonToObject(/*Get Object item i */jsonObjects?.getJSONObject(
                        FoodEntry.COLLECTION
                    ), keyEntity
                    )
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