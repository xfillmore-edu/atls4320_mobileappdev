package com.example.recyclerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdemo.sample.SampleClass

class MainActivity : AppCompatActivity() {

    private var birdList = SampleClass.birdList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set up recycler view
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // define adapter
        val adapter = BirdAdapter(birdList)

        // assign adapter to recycler view
        // this line can be combined with the previous line
        recyclerView.adapter = adapter

        // set layout manager on recycler view
        // vertical is default, doesn't have to be specified
        // <this> refers to the class instance
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // (optional) add line between rows
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))


    }
}