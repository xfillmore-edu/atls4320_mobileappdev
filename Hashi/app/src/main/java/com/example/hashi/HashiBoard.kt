package com.example.hashi

import android.util.Log
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
// https://developer.android.com/reference/android/view/WindowMetrics#getBounds()
// https://kotlinlang.org/docs/collection-filtering.html#test-predicates

class HashiBoard {
    // assumes vertical/portrait orientation as default
    val numCols: Int = Random.nextInt(7, 10)
    val numRows: Int = Random.nextInt(7, 12)
    private val mapCols = IntArray(numCols) { -1 } // create array of <numCols> -1's
    var boardByIdentifiers = Array(numRows) { mapCols } // stack the columns into <numRows> rows

    lateinit var nodeMap: List<HashiNode>

    fun createGameBoard() {

        val maxNodes: Int = (numCols * numRows) / 2
        val minNodes: Int = (numCols * numRows) / 7

        do {
            Log.i("Board", "Attempting new board proposal")
            // returns list of nodes
            nodeMap = proposeBoardSetup(minNodes, maxNodes)
        } while (!runTestSuite())

        // get screen size...?

        // need clickable images
        // display board according to grid

        // grid view?
    }

    private fun proposeBoardSetup (minNodes: Int, maxNodes: Int) : List<HashiNode> {
        // decide starting number of nodes/islands
        val nNodes: Int = Random.nextInt(minNodes, maxNodes)
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
            } // end for loop: check proposed position against existing node positions

            // create node if position deemed valid with id value <homeless>
            if (!skipflag) {
                val newNode = HashiNode(nodeTrackingList.size, xind, yind)
                nodeTrackingList.add(newNode)
                newMap[yind][xind] = newNode.isIdentifier
                Log.i("Board_propose", "Established node ${newNode.isIdentifier} at r${yind}c${xind}")
            }
        } // end for loop: attempt to create a node up to the maximum number allowed

        // ========= 3. assign edges ================== //
        // ========= 4. remove lonely ================= //
        // ========= 5. generate bridges ============== //
        // use node.expectedBridges [f, f, f, f, f, f, f, f]
        val removeIndices = mutableListOf<Int>()
        for ((deleteIndex, lonely) in nodeTrackingList.withIndex()) {
            val jcol = lonely.isLoc[0]
            val irow = lonely.isLoc[1]

            var nextLeftNeighbor = -1
            var nextRightNeighbor = -1
            var nextUpNeighbor = -1
            var nextDownNeighbor = -1

            // get current row and column of "lonely" node
            val ccol = IntArray(numRows)
            for ((index, el) in newMap.withIndex()) {
                ccol[index] = el[jcol]
            }
            val crow = newMap[irow]

            // find its neighbors

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

            // check if node has no neighbors. Mark it for removal then continue to next node
            if ((nextLeftNeighbor == -1) && (nextUpNeighbor == -1) &&
                (nextDownNeighbor == -1) && (nextRightNeighbor == -1)) {

                removeIndices.add(deleteIndex)
                newMap[irow][jcol] = -1
                continue
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

                    // mark map with the bridge (using -2)
                    // above current node, below neighbor node
                    for (bridgeSegment in nextUpNeighbor+1 until irow) {
                        newMap[bridgeSegment][jcol] = -2
                    }
                } // end if: check already bridged
            } // end if: bridge up neighbor
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

                    // mark map with the bridge (using -2)
                    // right of current node, left of neighbor node
                    for (bridgeSegment in jcol+1 until nextRightNeighbor) {
                        newMap[irow][bridgeSegment] = -2
                    }
                } // end if: check already bridged
            } // end if: bridge right neighbor
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

                    // mark map with the bridge (using -2)
                    // below current node, above neighbor node
                    for (bridgeSegment in irow+1 until nextDownNeighbor) {
                        newMap[bridgeSegment][jcol] = -2
                    }
                } // end if: check already bridged
            } // end if: bridge down neighbor
            numBridges = mockWeightedBridging[Random.nextInt(0, 4)]
            if ((nextLeftNeighbor > -1) && (numBridges > 0)) {
                lonely.neighbors[3] = nextLeftNeighbor
                nodeTrackingList[nextLeftNeighbor].neighbors[1] = lonely.isIdentifier

                // check if bridge already built between these two islands
                // if not, establish bridge
                if (!lonely.expectedBridges[6]) {
                    lonely.expectedBridges[6] = true
                    nodeTrackingList[nextLeftNeighbor].expectedBridges[2] = true
                    if (numBridges == 2) {
                        lonely.expectedBridges[7] = true
                        nodeTrackingList[nextLeftNeighbor].expectedBridges[3] = true
                    }

                    // mark map with the bridge (using -2)
                    // left of current node, right of neighbor node
                    for (bridgeSegment in nextLeftNeighbor+1 until jcol) {
                        newMap[irow][bridgeSegment] = -2
                    }
                } // end if: check already bridged
            } // end if: bridge left neighbor

            // check if no bridges were built for node
            // mark it for removal
            if (lonely.expectedBridges.none{ it }) {
                removeIndices.add(deleteIndex)
                newMap[irow][jcol] = -1
                continue
            }

        } // end for loop: main bridge building for each node

        // remove isolated nodes from nodeList
        // should probably go backwards so indexing isn't affected by changing nodeList
        for (delIndex in removeIndices.reversed()) {
            nodeTrackingList.removeAt(delIndex)
        }

        // update numeric mapping
        boardByIdentifiers = newMap

        // Hashi rule that all nodes are interconnected: use trace test

        // ==================================================== //
        // ============ 6. Island values ====================== //
        // ==================================================== //
        // assign nodes their island values based on their bridges
        for (island in nodeTrackingList) {
            var value = 0
            for (bridge in island.expectedBridges) {
                if (bridge) { value += 1 }
            }
            island.isVal = value
        }

        return nodeTrackingList
        // nodeTrackingList.size is the number of successfully placed islands
    } // end function: proposeBoardSetup

    private fun runTestSuite() : Boolean {
        return (
//            testNoDirectNeighbors() &&
//            testValidCorners() &&
//            testValidWalls() &&
//            testNoOnesByEights() &&
//            testNoTrappedThrees() &&
//            testNoTrappedFours() &&
            testExistsValidSolution()
        )
//        return true // placeholder
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

        // use nodeMap + graph checking algorithm
        // node.neighbors[i].isIdentifier

        // start at first node, mark visited
        // go to one neighbor, mark visited
        // depth first search: continue down the connected neighbors
        // when all neighbors of a node are visited, return up a level

        // set up graph checker
        data class TempNode (val id: Int, var visited: Boolean) {}
        val checker = mutableSetOf<TempNode>()
        for (node in nodeMap) {
            checker.add(TempNode(node.isIdentifier, false))
        }

        // set up depth first search
        fun dfs (node: HashiNode) {
            val thisNode = checker.find { it.id == node.isIdentifier }

            if (thisNode != null) {
                // mark current node as visited
                thisNode.visited = true

                // recursively visit neighbors
                for (neighbor in node.neighbors) {
                    if (neighbor != -1) {
                        val nb = nodeMap.find { it.isIdentifier == neighbor }
                        if (nb != null) {
                            val cnb = checker.find { it.id == neighbor }
                            if (!cnb?.visited!!) {
                                dfs(nb)
                            }
                        }
                        else {
                            Log.e("Testing_DFS", "Unable to locate neighbor node")
                        }
                    }
                }
            }
            else {
                Log.e("Testing_DFS", "Unable to locate node in checker")
            }
        } // end local function: dfs

        // run recursion to mark visited nodes
        dfs(nodeMap[0])

        // check if all nodes were visited
        if (checker.all { it.visited }) {
            return true
        }

        // default condition
        return false
    }
}