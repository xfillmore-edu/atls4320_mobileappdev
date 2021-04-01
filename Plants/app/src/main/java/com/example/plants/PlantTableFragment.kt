package com.example.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plants.model.ListAdapter
import com.example.plants.model.Phyla

class PlantTableFragment: Fragment() {

    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.plant_table, container, false)
    }

    // https://medium.com/inside-ppl-b7/recyclerview-inside-fragment-with-android-studio-680cbed59d84
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val phylaCommonName = bundle!!.getString("phylaname")
        val phylaMembers = bundle.getStringArrayList("phylamembers") as ArrayList<String>

        val recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        adapter = phylaMembers?.let { ListAdapter(it) }!!
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val ptitle = view.findViewById<View>(R.id.pageLabel) as TextView
        ptitle.text = phylaCommonName
    }

    // https://stackoverflow.com/a/53965039
    companion object {
        const val ARGN = "name"
        const val ARGM = "members"
        fun newInstance(name: String, members: ArrayList<String>): PlantTableFragment {
            val fragment = PlantTableFragment()

            val bundle = Bundle().apply {
                putString(ARGN, name)
                putStringArrayList(ARGM, members)
            }
            fragment.arguments = bundle

            return fragment
        }
    }
}