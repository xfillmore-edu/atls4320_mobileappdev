package com.example.plants

import android.content.Intent
import android.os.Bundle
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
        when (item.itemId) {
            R.id.action_phylapterido -> {
                reloadPhyla("pteridophytes")
                return true }
            R.id.action_phylabryo -> {
                reloadPhyla("bryophytes")
                return true }
            R.id.action_phylagymno -> {
                reloadPhyla("gymnosperms")
                return true }
            R.id.action_phylaangio -> {
                reloadPhyla("angiosperms")
                return true }
            else -> super.onOptionsItemSelected(item)
        }
        return false
    }

    private fun reloadPhyla(phy: String) {
        intent = Intent(this, PlantTableFragment::class.java)
        intent.putExtra("phyladat", phy)
        startActivity(intent)
    }
}