package com.example.apocalypse.util

import android.content.Context
import com.example.apocalypse.model.Item

class SharedPreferencesData {
    private val preferenceFile = "SUPPLIES"

    fun saveData (supplyList: ArrayList<Item>, context: Context) {
        // access shared pref file
        val sp = context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE)

        // editor for writing to shared pref file
        val ed = sp.edit()
        if (supplyList != null) {
            ed.putInt("numItems", supplyList.size)
        }

        // put items into strings of data
        for ((ind, item) in supplyList.withIndex()) {
            ed.putString("sn$ind", item.name)
        }

        // save data
        ed.apply()
    }

    fun loadData (context: Context) : ArrayList<Item> {
        var returningList = ArrayList<Item>()

        // access shared prefs file
        val sp = context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE)
        val size = sp.getInt("numItems", 0)

        // pull items
        for (i in 0 until size) {
            returningList.add(Item(sp.getString("sn$i", "")!!))
        }

        return returningList
    }

}