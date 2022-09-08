package mst

import java.io.*

data class Node(val n1: Int, val n2: Int, var weight: Long)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (v, e) = readLine().split(" ").map { it.toInt() }
    val nodeList = Array(e) {
        val (n1, n2, w) = readLine().split(" ").map { it.toInt() }
        Node(n1 = n1, n2 = n2, weight = w.toLong())
    }.sortedBy {
        it.weight
    }.toTypedArray()

    val parents = IntArray(v + 1) { it }

    fun getParent(a: Int): Int {
        if (parents[a] == a) return a
        val parent = getParent(parents[a])
        parents[a] = parent
        return parents[a]
    }

    fun isSameParent(a: Int, b: Int): Boolean = (getParent(a) == getParent(b))

    fun unionParents(a: Int, b: Int) {
        val parentA = getParent(a)
        val parentB = getParent(b)
        if (parentA > parentB) {
            parents[parentA] = parentB
        } else {
            parents[parentB] = parentA
        }
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var weightSum = 0L

        nodeList.forEach { (n1, n2, w) ->
            if (!isSameParent(n1, n2)){
                unionParents(n1, n2)
                weightSum += w
            }
        }
        w.write("$weightSum")
        w.flush()
    }

    close()

}
