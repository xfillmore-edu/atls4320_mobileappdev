package com.example.demoviewmodellivedata

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoviewmodellivedata.model.Item
import com.example.demoviewmodellivedata.model.ItemViewModel

class MainActivity : AppCompatActivity() {

    // "by" replaces need for factory class
    // instantiated with ()
    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            // use alert dialog
            val dialog = AlertDialog.Builder(this)
            val editText = EditText(applicationContext)
            dialog.setView(editText)
            dialog.setTitle(R.string.addItem)
            dialog.setPositiveButton(R.string.add)

        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = ListAdapter(viewModel)
        recyclerView.adapter = adapter

        // create obesrver
        viewModel.itemList.observe(this, Observer {
             adapter.update()
        })
    }

    fun setupRecyclerView() {
        //
    }














    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}