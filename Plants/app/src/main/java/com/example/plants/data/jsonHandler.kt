package com.example.plants

import android.content.Context
import com.example.plants.model.Phyla
import org.json.JSONException
import org.json.JSONObject

class jsonHandler() {
    private var plants = ArrayList<Phyla>()

    fun getJSON(context: Context): ArrayList<Phyla> {
        var json = loadJSONresource(context)
        plants = parseJSON(json)
        return plants
    }

    // returns JSON in flat string format
    fun loadJSONresource(context: Context): String {
        val istream = context.resources.openRawResource(R.raw.plants)
        val jsonAsString = istream.bufferedReader().use { it.readText() }
        return jsonAsString
    }

    fun parseJSON(jsonString: String): ArrayList<Phyla> {
        try {
            val jsonObject = JSONObject(jsonString)

            for (p in 0 until jsonObject.length()) {
                val phyname = when (p) {
                    0 -> "gymnosperms"
                    1 -> "pteridophytes"
                    2 -> "bryophytes"
                    3 -> "angiosperms"
                    else -> ""
                }
                val phy = jsonObject.getJSONObject(phyname)
                val alt = phy.getString("altname")
                val mem = phy.getJSONArray("members")

                var memAsArrStr = ArrayList<String>()
                for (i in 0 until mem.length()) {
                    memAsArrStr[i] = mem[i].toString()
                }

                val newPhyla = Phyla(phyname, alt, memAsArrStr)
                plants.add(newPhyla)
            }
        }
        catch (err: JSONException){
            err.printStackTrace()
        }
        return plants
    }


}