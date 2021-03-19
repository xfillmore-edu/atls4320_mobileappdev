package com.example.hashi

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*


// ================ Resources =====================
// https://www.tutorialspoint.com/how-to-draw-a-line-in-android-using-kotlin
// https://stackoverflow.com/questions/2902640/android-get-the-screen-resolution-pixels-as-integer-values

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // should set onClicks in HashiBoard probably
        // detect for first tap then set second tap as destination
        // or if first and second tap are same node, treat as deselection
        // or if second tap is not on a node, treat as deselection
        isNode1.setOnClickListener(View.OnClickListener {
            // change node's color because that's an obvious change
            isNode1.setImageResource(R.drawable.hashi_is2_256ylw)

            // draw a line from the node
            // (in implementation) determine dimensions of the matrix grid and define each
            // element in grid as a canvas that can contain a portion of the bridge line to the
            // next node (allows for different directions occurring)
            val bitmap = Bitmap.createBitmap(512, 10, Bitmap.Config.RGB_565)
            val canvas = Canvas(bitmap)
            canvas.drawColor(Color.DKGRAY) // figure out custom colors
            val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 4F // figure out what the size dimension is
            paint.isAntiAlias = true // figure out what this means
            // should set startX, startY, endX, endY to variables to leave this line static
            canvas.drawLine(112.toFloat(), 80.toFloat(), (canvas.width - 112).toFloat(), 80.toFloat(), paint)
            bridgeLine.setImageBitmap(bitmap)
        })

    }
}