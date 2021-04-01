package com.example.plants

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.plants.model.Phyla

class MainActivity : AppCompatActivity() {

    var fulldata = ArrayList<Phyla>()
    private lateinit var ftrans: FragmentTransaction
    private lateinit var destination: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // https://www.tutorialspoint.com/how-to-send-a-variable-from-activity-to-fragment-in-android-using-kotlin
        // https://developer.android.com/guide/fragments/transactions
        val fman: FragmentManager = supportFragmentManager
        ftrans = fman.beginTransaction()
        destination = PlantTableFragment()

        val fulldata = jsonHandler().getJSON(this)
        reloadPhyla(0) // initial view data

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
                reloadPhyla(0)
                return true }
            R.id.action_phylabryo -> {
                reloadPhyla(1)
                return true }
            R.id.action_phylagymno -> {
                reloadPhyla(2)
                return true }
            R.id.action_phylaangio -> {
                reloadPhyla(3)
                return true }
            else -> super.onOptionsItemSelected(item)
        }
        return false
    }

    private fun reloadPhyla(phy: Int) {
        val bundle = Bundle()
        bundle.putString("phylaname", fulldata[phy].altname)
        bundle.putStringArrayList("phylamembers", fulldata[phy].members)

        destination.arguments = bundle
        ftrans.add(R.id.plant_table, PlantTableFragment).commit()
    }
}