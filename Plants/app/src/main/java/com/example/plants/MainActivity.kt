package com.example.plants

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.plants.model.Phyla

class MainActivity : AppCompatActivity() {

    var fulldata = ArrayList<Phyla>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val fulldata = jsonHandler().getJSON(this)
        reloadPhyla("pteridophytes") // initial view data

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
            R.id.action_phylapterido -> true // reloadPhyla("pteridophytes")
            R.id.action_phylabryo -> true // reloadPhyla("bryophytes")
            R.id.action_phylagymno -> true // reloadPhyla("gymnosperms")
            R.id.action_phylaangio -> true // reloadPhyla("angiosperms")
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun reloadPhyla(phy: String) {
        //
    }
}