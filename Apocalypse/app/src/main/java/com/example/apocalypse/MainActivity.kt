package com.example.apocalypse
// an app for monitoring your supplies during an apocalypse

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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apocalypse.model.Item
import com.example.apocalypse.model.SupplyViewModel

class MainActivity : AppCompatActivity() {

    private val supplyViewModel: SupplyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // recycler view setup
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        val adapter = RVAdapter(supplyViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

            // alert dialog with text field for new item
            val dialog = AlertDialog.Builder(this)
            val editText = EditText(applicationContext)
            dialog.setView(editText)
            dialog.setTitle(R.string.adding)

            dialog.setPositiveButton(R.string.confirm) { _, _ ->
                val newSupply = editText.text.toString()
                if (newSupply.isNotEmpty()) {
                    supplyViewModel.packSupply(Item(newSupply))

                    Snackbar.make(view, R.string.added, Snackbar.LENGTH_LONG)
                        .setAction(R.string.action, null).show()
                }
            }
            dialog.setNegativeButton(R.string.cancel) { _, _ -> }

            dialog.show()
        }

        // LiveData observer
        supplyViewModel.supplyList.observe(this, Observer {
            adapter.update()
            supplyViewModel.saveData()
        })

        // shared preferences data management
        if (supplyViewModel.supplyList.value == null) {
            supplyViewModel.loadData()
        }
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