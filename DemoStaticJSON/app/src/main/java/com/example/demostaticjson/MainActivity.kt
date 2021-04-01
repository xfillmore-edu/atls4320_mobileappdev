package com.example.demostaticjson

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demostaticjson.data.ImportableData
import com.example.demostaticjson.model.ListAdapter
import com.example.demostaticjson.model.Person

class MainActivity : AppCompatActivity() {

    var potterList = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // problem with using .isEmpty()....
        // potterList is always empty when instance is recreated
        // because destroys instance
        if (potterList.isEmpty()) {
            val data = ImportableData()

            // populate with JSON data
            potterList = data.getJSON(this) // this = main activity
        }

        // debug needs?
        // Log.i, Log.d, etc
        // Log.i("note", variablestuff)

        // get recycler view
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // divider line between rows
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        // define adapter
        val adapter = ListAdapter(potterList, {item: Person -> itemClicked(item)})

        // assign adapter to recycle view
        recyclerView.adapter = adapter

        // set layour manager on recycler view
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    // implicit intent (external to app)
    private fun itemClicked(item: Person) {
        // create intent
        var intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(item.info)

        // start activity
        startActivity(intent)
    }
}