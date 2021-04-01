package com.example.recyclerdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdemo.model.Bird
import com.example.recyclerdemo.sample.SampleClass

class MainActivity : AppCompatActivity() {

    private var birdList = SampleClass.birdList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set up recycler view
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // define adapter
        // BirdAdapter now takes two args, 2nd is lambda expression
        // arrow to body
        // item is type Bird
        // passes item into the event
        val adapter = BirdAdapter(birdList, {item: Bird -> itemClicked(item)})

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

    private fun itemClicked(item : Bird) {
        // create intent
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name", item.name)
        intent.putExtra("resourceID", item.imageResourceID)

        // start activity
        startActivity(intent)

        // startActivityForResult()
        // used when activity you're going to will pass data back
    }
}