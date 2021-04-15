package com.example.foxed.util

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.HttpResponse
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.foxed.model.FoxViewModel
import org.json.JSONException
import org.json.JSONObject

class DataJSON {
    val furl = "https://randomfox.ca/?i="

    fun loadJSON (context: Context, foxViewModel: FoxViewModel, imgid: Int) {
        val url = furl + imgid.toString()

        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                { response ->
                    parseJSON(response, FoxViewModel)
                },
                {
                    Log.e("Error", error("Request failed."))
                }
        )

        queue.add(stringRequest)
    }

    fun parseJSON(response: String, viewModel: FoxViewModel) {
        val imgurl = "https://randomfox.ca/images/"
        try {
            val jsonObject = JSONObject(response)
            val result = jsonObject.getJSONArray("results")
            Log.i("JSON response", result as String)
        }
        catch (err: JSONException) {
            err.printStackTrace()
        }

    }
}