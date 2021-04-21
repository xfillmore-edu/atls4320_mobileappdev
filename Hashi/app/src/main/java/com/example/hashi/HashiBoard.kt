package com.example.hashi

import kotlin.math.absoluteValue
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
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/down-to.html

class HashiBoard {
    private val numCols: Int = Random.nextInt(7, 10)
    private val numRows: Int = Random.nextInt(7, 12)

    private val maxNodes: Int = (numCols * numRows) / 2
    private val minNodes: Int = (numCols * numRows) / 7

    private var mapCols = IntArray(numCols) { -1 } // create array of <numCols> -1's
    private var numericMap = Array(numRows) { mapCols } // stack the columns into <numRows> rows

    fun createGameBoard() {
        do {
            numericMap = proposeBoardSetup()
        } while (!runTestSuite())

        // turn numericMap into 2D Array/Matrix of HashiNodes
    }

    private fun proposeBoardSetup () : Array<IntArray> {
        // decide starting number of nodes/islands
        var nNodes: Int = Random.nextInt(minNodes, maxNodes)
//        var nodeValues = IntArray(nNodes) {0}
        val nodeTrackingList = mutableListOf <HashiNode>()

        // create matrix map for node positions
        val newMap = Array(numRows) { mapCols }

        // 1. place nNodes islands on matrix board
        // 2. remove any adjacent islands
        // 3. assign neighbors/edges based on matrix grid
        // 4. remove any islands that have no connections
        // 5. calculate/generate bridges as random(1,2)
        // 6. calculate/assign island values based on their bridges
        // 7. update image for island based on light/dark theme

        // ========= 1. place nNodes ===================== //
        // ========= 2. remove adjacents ================= //
        for (homeless in 0 until nNodes) {
            val xind = Random.nextInt(0, numCols)
            val yind = Random.nextInt(0, numRows)

            // check validity of position
            var skipflag = false
            for (homed in nodeTrackingList) {
                // check if another node already exists at that location on the board
                if ((xind == homed.isLoc[0]) && (yind == homed.isLoc[1])) {
                    skipflag = true
                    break
                }
                // check if the proposed location is adjacent in the x direction
                if (((xind - homed.isLoc[0]).absoluteValue < 2) && (yind == homed.isLoc[1])) {
                    skipflag = true
                    break
                }
                // check if the proposed location is adjacent in the y direction
                if (((yind - homed.isLoc[1]).absoluteValue < 2) && (xind == homed.isLoc[0])) {
                    skipflag = true
                    break
                }
            }

            // create node if position deemed valid with id value <homeless>
            if (!skipflag) {
                val newNode = HashiNode(nodeTrackingList.size, xind, yind)
                nodeTrackingList.add(newNode)
                newMap[yind][xind] = newNode.isIdentifier
            }
        }

        // update nNodes to the number of nodes that were successfully placed
        nNodes = nodeTrackingList.size

        // ========= 3. assign edges ================== //
        // ========= 4. remove lonely ================= //
        // ========= 5. generate bridges ============== //
        // use node.expectedBridges [f, f, f, f, f, f, f, f]
        for (lonely in nodeTrackingList) {
            val jcol = lonely.isLoc[0]
            val irow = lonely.isLoc[1]

            var nextLeftNeighbor = -1
            var nextRightNeighbor = -1
            var nextUpNeighbor = -1
            var nextDownNeighbor = -1

            // find its neighbors
            val ccol = IntArray(numRows)
            for ((index, el) in newMap.withIndex()) {
                ccol[index] = el[jcol]
            }
            val crow = newMap[irow]

            // going right
            if (jcol < ccol.size-1) {
                for (iter in jcol+1 until ccol.size) {
                    if (ccol[iter] > -1) {
                        nextRightNeighbor = iter
                        break
                    }
                }
            }
            // going left
            if (jcol > 0) {
                for (iter in jcol-1 downTo 0) {
                    if (ccol[iter] > -1) {
                        nextLeftNeighbor = iter
                        break
                    }
                }
            }
            // going up
            if (irow > 0) {
                for (iter in irow-1 downTo 0) {
                    if (crow[iter] > -1) {
                        nextUpNeighbor = iter
                        break
                    }
                }
            }
            // going down
            if (irow < crow.size-1) {
                for (iter in irow+1 until crow.size) {
                    if (crow[iter] > -1) {
                        nextDownNeighbor = iter
                        break
                    }
                }
            }


            // exchange neighborly information
            // randomly decide whether to establish 0, 1, or 2 bridge(s)
            // mark map with -2 where bridges are drawn to prevent overlap
            // up up right right down down left left
            val mockWeightedBridging = intArrayOf(0, 1, 1, 2)
            var numBridges = mockWeightedBridging[Random.nextInt(0, 4)]
            if ((nextUpNeighbor > -1) && (numBridges > 0)) {
                lonely.neighbors[0] = nextUpNeighbor
                nodeTrackingList[nextUpNeighbor].neighbors[2] = lonely.isIdentifier

                // check if bridge already built between these two islands
                if (!lonely.expectedBridges[0]) {
                    lonely.expectedBridges[0] = true
                    nodeTrackingList[nextUpNeighbor].expectedBridges[4] = true
                    if (numBridges == 2) {
                        lonely.expectedBridges[1] = true
                        nodeTrackingList[nextUpNeighbor].expectedBridges[5] = true
                    }
                }

                // mark map with the bridge (using -2)
                //
            }
            numBridges = mockWeightedBridging[Random.nextInt(0, 4)]
            if ((nextRightNeighbor > -1) && (numBridges > 0)) {
                lonely.neighbors[1] = nextRightNeighbor
                nodeTrackingList[nextRightNeighbor].neighbors[3] = lonely.isIdentifier

                // check if bridge already built between these two islands
                if (!lonely.expectedBridges[2]) {
                    lonely.expectedBridges[2] = true
                    nodeTrackingList[nextRightNeighbor].expectedBridges[6] = true
                    if (numBridges == 2) {
                        lonely.expectedBridges[3] = true
                        nodeTrackingList[nextRightNeighbor].expectedBridges[7] = true
                    }
                }

                // mark map with the bridge (using -2)
                //
            }
            numBridges = mockWeightedBridging[Random.nextInt(0, 4)]
            if ((nextDownNeighbor > -1) && (numBridges > 0)) {
                lonely.neighbors[2] = nextDownNeighbor
                nodeTrackingList[nextDownNeighbor].neighbors[0] = lonely.isIdentifier

                // check if bridge already built between these two islands
                if (!lonely.expectedBridges[4]) {
                    lonely.expectedBridges[4] = true
                    nodeTrackingList[nextDownNeighbor].expectedBridges[0] = true
                    if (numBridges == 2) {
                        lonely.expectedBridges[5] = true
                        nodeTrackingList[nextDownNeighbor].expectedBridges[1] = true
                    }
                }

                // mark map with the bridge (using -2)
                //
            }
            numBridges = mockWeightedBridging[Random.nextInt(0, 4)]
            if ((nextLeftNeighbor > -1) && (numBridges > 0)) {
                lonely.neighbors[3] = nextLeftNeighbor
                nodeTrackingList[nextLeftNeighbor].neighbors[1] = lonely.isIdentifier

                // check if bridge already built between these two islands
                if (!lonely.expectedBridges[6]) {
                    lonely.expectedBridges[6] = true
                    nodeTrackingList[nextLeftNeighbor].expectedBridges[2] = true
                    if (numBridges == 2) {
                        lonely.expectedBridges[7] = true
                        nodeTrackingList[nextLeftNeighbor].expectedBridges[3] = true
                    }
                }

                // mark map with the bridge (using -2)
                //
            }



        }


        return newMap
    }

    private fun runTestSuite() : Boolean {
//        return (
//            testNoDirectNeighbors() &&
//            testValidCorners() &&
//            testValidWalls() &&
//            testNoOnesByEights() &&
//            testNoTrappedThrees() &&
//            testNoTrappedFours() &&
//            testExistsValidSolution()
//        )
        return true
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