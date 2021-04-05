package com.example.demoviewmodellivedata.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {
    val itemList = MutableLiveData<ArrayList<Item>>()

    // reference list - operate on this to update MutableLiveData list
    // kotlin pass by reference so doesn't recognize changes to original
    private var newList = ArrayList<Item>()

    fun add(item: Item) {
        newList.add(item)
        itemList.value = newList
    }

    fun delete(item: Item) {
        newList.remove(item)
        itemList.value = newList
    }
}