package com.example.demostaticjson.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demostaticjson.R

class ListAdapter(private val characterList: ArrayList<Person>, private val clickListener: (Person)->Unit): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemTextView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // called by adapter each time needs to display view item
        // then wrap in instance of viewholder

        // create instance of LayoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)

        // inflate view
        val itemViewHolder = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(itemViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get data at position
        val item = characterList[position]

        // set text of textView to name
        holder.itemTextView.text = item.name

        // assign click listener
        holder.itemView.setOnClickListener{clickListener(item)}
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}