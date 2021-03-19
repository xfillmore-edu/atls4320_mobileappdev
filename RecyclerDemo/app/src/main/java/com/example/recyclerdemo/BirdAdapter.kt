package com.example.recyclerdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdemo.model.Bird
import com.example.recyclerdemo.sample.SampleClass.birdList

// class ClassName (primary constructor params) : Subclasses these Parents () {...}
class BirdAdapter (private val bulbList: ArrayList<Bird>) : RecyclerView.Adapter<BirdAdapter.ViewHolder>() {

    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        // the id matches the text view id in list_item
        // would be more complex here if more content
        // with data member for each individual item
        val birdTextView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // creates new ViewHolder object whenever recyclerView needs one
        // equivalent to cellForRowAt create

        // create instance of LayoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)

        // inflate the view
        val itemViewHolder = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // takes ViewHolder object, sets list data
        // equivalent to cellForRowAt set data

        // get data at the position
        val bird = birdList[position]

        // set text of the textView to the name
        holder.birdTextView.text = bird.name
    }

    override fun getItemCount() = birdList.size
//    override fun getItemCount(): Int {
//        // number of items in list
//        // equivalent to numberOfRowsInSection
//    }
    // will be implementing view holder class
}