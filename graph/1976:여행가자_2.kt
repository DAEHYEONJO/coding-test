package graph

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    fun String.toBoolean() = (this == "1")

    val n = readLine().toInt()
    val m = readLine().toInt()
    val parents = IntArray(n + 1) {
        it
    }

    fun getParent(a: Int): Int {
        if (parents[a] == a) return a
        val parent = getParent(parents[a])
        parents[a] = parent
        return parent
    }

    fun unionParents(a: Int, b: Int) {
        val parentA = getParent(a)
        val parentB = getParent(b)
        if (parentA > parentB) {
            parents[parentA] = parentB
        } else {
            parents[parentB] = parentA
        }
    }

    fun isSameParents(a: Int, b: Int) = (getParent(a) == getParent(b))

    repeat(n) { row ->
        readLine().split(" ").map { it.toBoolean() }.forEachIndexed { col, connected ->
            if (row < col) {
                if (connected && !isSameParents(row + 1, col + 1)){
                    unionParents(row + 1, col + 1)
                }
            }
        }
    }

    val travelNodes = readLine().split(" ").map { it.toInt() }.toIntArray()

    for (i in 0 until travelNodes.size-1){
        if (!isSameParents(travelNodes[i], travelNodes[i+1])){
            println("NO")
            return@with
        }
    }

    println("YES")
}