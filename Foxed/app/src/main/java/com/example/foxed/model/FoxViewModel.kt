package com.example.foxed.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoxViewModel : ViewModel() {
    val foxId = MutableLiveData<Int>()
}