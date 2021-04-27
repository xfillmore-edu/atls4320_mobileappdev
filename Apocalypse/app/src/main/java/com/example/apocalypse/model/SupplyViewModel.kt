package com.example.apocalypse.model

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apocalypse.util.SharedPreferencesData

class SupplyViewModel (application: Application) : AndroidViewModel(application) {
    val supplyList = MutableLiveData<ArrayList<Item>>()
    private var newList = ArrayList<Item>()

    fun packSupply(sp: Item) {
        newList.add(sp)
        supplyList.value = newList
    }

    fun useSupply(sp: Item) {
        newList.remove(sp)
        supplyList.value = newList
    }

    // shared preferences content
    @SuppressLint("StaticFieldLeak")
    val context: Context = application.applicationContext
    private val spData = SharedPreferencesData()

    fun loadData() {
        newList = spData.loadData(context)
        supplyList.value = newList
    }

    fun saveData() {
        supplyList.value?.let {
            spData.saveData(supplyList.value!!, context)
        }
    }
}