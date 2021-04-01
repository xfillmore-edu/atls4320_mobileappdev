package com.example.demostaticjson.data

import com.example.demostaticjson.model.Person
import android.content.Context
import com.example.demostaticjson.R
import org.json.JSONException
import org.json.JSONObject

// doesn't have to be a data class even though handling data
class ImportableData {
    // array list of type Person, the model class
    // at class level
    var characterList = ArrayList<Person>()

    fun getJSON(context: Context): ArrayList<Person> {
        var json = loadJSONFromRes(context)
        characterList = parseJSON(json)
        return characterList
    }

    fun loadJSONFromRes(context: Context): String {
        // open raw JSON file and assign it to an InputStream instance
        val inputStream = context.resources.openRawResource(R.raw.harrypotter)

        // creates buffered reader on the InputStream and readText returns string
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        // returns to getJSON
        return jsonString
    }

    fun parseJSON(jsonString: String): ArrayList<Person> {
        // JSON object class throws error
        try {
            // pay attention to data structure heirarchy
            // create JSONObject
            val jsonObject = JSONObject(jsonString)
            // create JSONArray with value from characters key
            val characterArray = jsonObject.getJSONArray("characters")

            // loop through each object in the array
            // JSON array doesn't have iterator (can't use for...in)
            for (i in 0 until characterArray.length()) {
                val item = characterArray.getJSONObject(i)

                // get values for name and info keys
                val name = item.getString("name")
                val info = item.getString("info")

                // create new Character object
                val newCharacter = Person(name, info)

                // add character object to ArrayList
                characterList.add(newCharacter)
            }

        }
        catch (e: JSONException){
            e.printStackTrace()
        }

        return characterList
    }
}