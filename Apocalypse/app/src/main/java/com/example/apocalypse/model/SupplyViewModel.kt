package com.example.apocalypse.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SupplyViewModel : ViewModel() {
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
}