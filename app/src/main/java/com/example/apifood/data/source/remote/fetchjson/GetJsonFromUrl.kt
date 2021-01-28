package com.example.apifood.data.source.remote.fetchjson

import android.os.AsyncTask
import android.util.Log
import com.example.apifood.data.source.remote.OnFetchDataListener
import org.json.JSONObject
import java.lang.Exception

class GetJsonFromUrl<T>(
    private val listener: OnFetchDataListener<T>,
    private val keyEntity : String
):AsyncTask<String? , Void? , String?>() {
    private var exception : Exception? = null
    override fun doInBackground(vararg params: String?): String {

        var data = ""
        try {
            val parseDataWithJson = ParseDataWithJson()
            data = parseDataWithJson.getJonFromUrl(params[0])
            Log.e("TEST", "doIn")
        }catch (e : Exception){
            e.printStackTrace()
        }
        Log.e("TEST", "DOIN")
        return data
    }

    override fun onPostExecute(data: String?) {
        Log.e("TEST", "POST")
        super.onPostExecute(data)
        if (data!= null && data.isNotBlank()){
            val jsonObject =JSONObject(data)
            @Suppress("UNCHECKED_CAST")
            listener.onSuccess(ParseDataWithJson().parseJsonToData(jsonObject, keyEntity) as T)
        }else listener.onError(exception)

    }
}