package com.example.recyclerdemo.model

data class Bird(val name: String, val imageResourceID: Int) {
    // main purpose is to hold data - will automatically create and override: toString, equals, hashCode, copy
    // Any() class has those but otherwise must be specifically implemented
    // derived from properties in primary constructor
    // primary constructor must have at least one parameter, must be marked as val or var
    // class can not be abstract/open/sealed/inner
}
