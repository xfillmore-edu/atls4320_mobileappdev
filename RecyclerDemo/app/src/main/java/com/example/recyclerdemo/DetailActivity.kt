package com.example.recyclerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // get intent
        val name = intent.getStringExtra("name")
        val resourceID = intent.getIntExtra("resourceID", -1)

        // update view
        // should be an if statement checking if resourceID is not -1
        val birdImage: ImageView = findViewById(R.id.imageViewBird)
        birdImage.setImageResource(resourceID)
        val birdName: TextView = findViewById(R.id.textView2)
        birdName.text = name
    }
}