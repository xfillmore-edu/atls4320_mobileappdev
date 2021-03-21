package com.example.hashi

import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.example.hashi.R.drawable.*

// ==============   Resources   ====================
// https://kotlinlang.org/docs/basic-types.html#numbers
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean-array/
// https://stackoverflow.com/a/59177156
// https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable

class HashiNode(val isVal: Int, isPosX: Int, isPosY: Int) {
    val isLoc: IntArray = intArrayOf(isPosX, isPosY)

    // get node's corresponding drawable resource to display
    // vary for light/dark theme
    val isDisplayOnLight: Int = when (isVal) {
        1 -> hashi_is1_256grey
        2 -> hashi_is2_256grey
        3 -> hashi_is3_256grey
        4 -> hashi_is4_256grey
        5 -> hashi_is5_256grey
        6 -> hashi_is6_256grey
        7 -> hashi_is7_256grey
        8 -> hashi_is8_256grey
        else -> hashi_is0_256grey
    }
    val isDisplayOnDark: Int = when (isVal) {
        1 -> hashi_is1_256ylw
        2 -> hashi_is2_256ylw
        3 -> hashi_is3_256ylw
        4 -> hashi_is4_256ylw
        5 -> hashi_is5_256ylw
        6 -> hashi_is6_256ylw
        7 -> hashi_is7_256ylw
        8 -> hashi_is8_256ylw
        else -> hashi_is0_256ylw
    }

    private var currentNumBridges = 0
    // N1 N2 E1 E2 S1 S2 W1 W2
    private var expectedBridges: BooleanArray = booleanArrayOf(false, false, false, false, false, false, false, false)
    private var linkedBridges: BooleanArray = booleanArrayOf(false, false, false, false, false, false, false, false)

    fun validateBridges () : Boolean {
        // doesn't account for checking bridge accuracy partially through
        // but that's probably out of project scope
        if (currentNumBridges < isVal) return false

        for ((ii, b) in expectedBridges.withIndex()) {
            if (linkedBridges[ii] != b) return false
        }

        return true
    }

    // Takes one of four directions as input
    // Returns whether bridge was drawn successfully (1), erased successfully (-1),
    // or operation failed entirely (0)
    // check return value for updating bridges visually
    fun drawBridge (dir: String, dest: HashiNode) : Int {
        // account for if the destination island cannot accept anymore bridges
        if (dest.currentNumBridges == dest.isVal) return 0

        // account for case of one island that already has a bridge
        if ((isVal == 1) && (currentNumBridges == 1)) {
            // check for matching direction with existing bridge
            // if bridge exists in that direction, treat as erase action
            val brLoc = linkedBridges.indexOf(true)

            if ((brLoc == 0) && (dir=="N")) {
                return eraseBridge(brLoc)
            }
            if ((brLoc == 2) && (dir=="E")) {
                return eraseBridge(brLoc)
            }
            if ((brLoc == 4) && (dir=="S")) {
                return eraseBridge(brLoc)
            }
            if ((brLoc == 6) && (dir=="W")) {
                return eraseBridge(brLoc)
            }

            // attempt was to draw bridge in a different direction from existing bridge
            return 0
        }

        // refuse to draw bridge if maximum bridges reached
        // *Don't implement here*
        // *prevents erasing bridges*
        val canDraw: Boolean = (currentNumBridges != isVal)

        // attempt to draw or erase bridge in specified direction
        // if first bridge doesn't exist and not maxed, draw the bridge
        // if first bridge does exist and maxed, erase the bridge
        // if second bridge doesn't exist and not maxed, draw the bridge
        // otherwise, the second bridge exists and should be erased
        when (dir) {
            "N" -> {
                if (!linkedBridges[0] && canDraw)      linkedBridges[0] = true
                else if (linkedBridges[0] && !canDraw) eraseBridge(0)
                else if (!linkedBridges[1] && canDraw) linkedBridges[1] = true
                else return eraseBridge(1)
            }
            "E" -> {
                if (!linkedBridges[2] && canDraw)      linkedBridges[2] = true
                else if (linkedBridges[2] && !canDraw) eraseBridge(2)
                else if (!linkedBridges[3] && canDraw) linkedBridges[3] = true
                else return eraseBridge(3)
            }
            "S" -> {
                if (!linkedBridges[4] && canDraw)      linkedBridges[4] = true
                else if (linkedBridges[4] && !canDraw) eraseBridge(4)
                else if (!linkedBridges[5] && canDraw) linkedBridges[5] = true
                else return eraseBridge(5)
            }
            "W" -> {
                if (!linkedBridges[6] && canDraw)      linkedBridges[6] = true
                else if (linkedBridges[6] && !canDraw) eraseBridge(6)
                else if (!linkedBridges[7] && canDraw) linkedBridges[7] = true
                else return eraseBridge(7)
            }
            else -> return 0
        }

        // increase bridge count and return success
        currentNumBridges += 1
        return 1
    }

    // takes index for linkedBridges array to handle
    // indexing system automatically takes care of direction
    // returns -1 for success, 0 for failed
    private fun eraseBridge (ind: Int) : Int {
        // will never happen but for safe redundancy
        if (currentNumBridges == 0) return 0

        // check that the bridge currently exists, and then erase
        if (linkedBridges[ind]) {
            linkedBridges[ind] = false

            // reduce bridge count
            currentNumBridges -= 1
            return -1
        }

        // failed to erase bridge because it did not exist
        return 0
    }

    // Out of project scope: rotate bridge links on rotation
    // Device turned clockwise -> shift bridges E to N etc
    // Device turned counterclockwise -> shift bridges E to S etc
    // to obtain rotationDir will need to get sensor information
    // and compare to previous orientation information
    // https://developer.android.com/guide/topics/sensors/sensors_position#sensors-pos-orient
    fun rotateBridges(rotationDir: String) {
        // stub function
    }


}