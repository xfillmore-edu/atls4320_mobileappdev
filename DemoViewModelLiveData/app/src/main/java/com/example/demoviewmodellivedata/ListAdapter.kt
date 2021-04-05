package com.example.demoviewmodellivedata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoviewmodellivedata.model.ItemViewModel

class ListAdapter(private val itemViewModel: ItemViewModel): RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private var myItemList = itemViewModel.itemList.value

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemTextView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create instance of layout inflater
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemViewHolder = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = myItemList?.get(position)
        holder.itemTextView.text = item?.name ?:""
    }

    override fun getItemCount(): Int {
//        return myItemList.size // doesn't work because optional

        if (myItemList != null) {
            return myItemList!!.size
        } else return 0
    }

    // works with Observer
    // go to data source to update local item list

    fun update () {
        myItemList = itemViewModel.itemList.value
        notifyDataSetChanged() // not recommended for more data
    }
}