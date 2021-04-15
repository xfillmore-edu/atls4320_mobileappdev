package com.example.hashi

import android.opengl.Matrix
import kotlin.math.floor
import kotlin.random.Random

// =========== Resources ==============
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.random/-random/
// https://developer.android.com/reference/kotlin/android/graphics/Matrix <- wrong type of Matrix
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.math/
// https://stackoverflow.com/a/34145673 <- declare array in kotlin
// https://www.geeksforgeeks.org/kotlin-array/
// https://kotlinlang.org/docs/basic-types.html#arrays
// https://kotlinlang.org/docs/control-flow.html#while-loops
// https://kotlinlang.org/docs/coding-conventions.html#function-names <- no underscore
// https://kotlinlang.org/docs/control-flow.html <- no ternary operator
// https://www.appsdeveloperblog.com/create-imagebutton-kotlin-programmatically/
// https://kotlin-android.com/kotlin-ranges/
// https://www.techiedelight.com/print-two-dimensional-array-kotlin/

class HashiBoard {
    private val numCols: Int = Random.nextInt(7, 10)
    private val numRows: Int = Random.nextInt(7, 12)

    private val maxNodes: Int = (numCols * numRows) / 2
    private val minNodes: Int = (numCols * numRows) / 7

    var mapCols = IntArray(numCols) { 0 } // create array of <numCols> 0's
    var numericMap = Array(numRows) { mapCols } // stack the columns into <numRows> rows

    fun createGameBoard() {
        do {
            numericMap = proposeBoardSetup()
        } while (!runTestSuite())

        // turn numericMap into 2D Array/Matrix of HashiNodes
    }

    private fun proposeBoardSetup () : Array<IntArray> {
        // decide starting number of nodes/islands
        val nNodes: Int = Random.nextInt(minNodes, maxNodes)
        var nodeValues = IntArray(nNodes) {0}

        // create matrix map for node positions
        var newMap = Array(numRows) { mapCols }

        // determine the values of the nodes to be placed on map
        // increase probability of placing 2's and 3's on map, minimize probability of 5-8
        // 8 7 6 5 4 1 2 3
        // .. operator is inclusive, until is exclusive
        for (node in 0 until nNodes) {
            if (Random.nextInt(0, numCols * numRows) == 1) {
                nodeValues[node] = 8
                continue
            }
            else if (Random.nextInt(0, numCols*(numRows-2)) == 1) {
                nodeValues[node] = 7
                continue
            }
            else if (Random.nextInt(0, (numCols-2)*(numRows-2))==1) {
                nodeValues[node] = 6
                continue
            }
            else if (Random.nextInt(0, (numCols-4)*(numRows-4))==1) {
                nodeValues[node] = 5
                continue
            }
            else if (Random.nextInt(0, (numCols-5)*(numRows-5))==1) {
                nodeValues[node] = 4
                continue
            }
            else if (Random.nextInt(0, (numCols-6)*(numRows-5))==1) {
                nodeValues[node] = 1
                continue
            }
            else {
                nodeValues[node] = if(Random.nextBoolean()) 3 else 2
            }
        }

        return newMap
    }

    private fun runTestSuite() : Boolean {
        return (
            testNoDirectNeighbors() &&
            testValidCorners() &&
            testValidWalls() &&
            testNoOnesByEights() &&
            testNoTrappedThrees() &&
            testExistsValidSolution()
        )
    }
    // test proposed board: return false if fails test
    // then create new proposal for setup and re-test
    private fun testNoDirectNeighbors () : Boolean {
        // test that no two nodes are directly adjacent in the matrix
        return false
    }
    private fun testValidCorners () : Boolean {
        // test that only nodes of 4, 3, 2, or 1 were placed in the corners
        return false
    }
    private fun testValidWalls () : Boolean {
        // test that only nodes of 6 or less were placed against walls
        return false
    }
    private fun testNoOnesByEights () : Boolean {
        // test that no nodes of value 1 occur next to a node of value 8
        return false
    }
    private fun testNoTrappedThrees () : Boolean {
        // test that if a 3 occurs in a corner, its only neighbors are not 2 and 1
        return false
    }
    private fun testNoTrappedFours () : Boolean {
        // test that if a 4 occurs in a corner, its only neighbors are not 2's
        return false
    }
    private fun testExistsValidSolution () : Boolean {
        // confirm that the board has a solution
        // all nodes must be able to connect into one unit
        return false
    }
}