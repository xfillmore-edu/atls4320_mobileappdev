package com.example.apocalypse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apocalypse.model.SupplyViewModel
import com.google.android.material.snackbar.Snackbar

class RVAdapter (private val supplyViewModel: SupplyViewModel): RecyclerView.Adapter<RVAdapter.ViewHolder> () {

    private var supplies = supplyViewModel.supplyList.value

    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val textV: TextView = view.findViewById(R.id.textView)
    }

    // context on how to correctly inflate view
    // requires context of parent view group aka RecyclerView
    // wrap view in instance of ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // instance of layoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemViewHolder = layoutInflater.inflate(R.layout.supply_itemrow, parent, false)
        return ViewHolder(itemViewHolder)
    }

    // called every time adapter displaying new item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val supply = supplies?.get(position)
        holder.textV.text = supply?.name ?: "" // cheating ternary

        // context menu (long press to delete)
        holder.itemView.setOnCreateContextMenuListener() {menu, view, menuInfo ->
            menu.setHeaderTitle(R.string.using)
            menu.add(R.string.use).setOnMenuItemClickListener {
                // remove from view model
                supplyViewModel.useSupply(supply!!)
                Snackbar.make(view, R.string.removed, Snackbar.LENGTH_LONG)
                    .setAction(R.string.action, null).show()
                true
            }
            menu.add(R.string.save)
        }
    }

    override fun getItemCount(): Int {
        if (supplies != null) {
            return supplies!!.size
        }
        return 0
    }

    fun update() {
        supplies = supplyViewModel.supplyList.value
        notifyDataSetChanged()
    }
}