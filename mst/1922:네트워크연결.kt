package mst

import java.io.*

data class Network(val c1: Int, val c2: Int, var weight: Long)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val computers = Array(m) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        Network(a, b, c.toLong())
    }.sortedBy {
        it.weight
    }.toTypedArray()

    val parents = IntArray(n + 1) { it }

    fun getParent(a: Int): Int{
        if (parents[a] == a) return a
        val parent = getParent(parents[a])
        parents[a] = parent
        return parent
    }

    fun unionParent(a: Int, b: Int){
        val pA = getParent(a)
        val pB = getParent(b)
        if (pA > pB){
            parents[pA] = pB
        }else{
            parents[pB] = pA
        }
    }

    fun isSameParent(a: Int, b: Int): Boolean = (getParent(a) == getParent(b))

    var answer = 0L

    computers.forEach { (c1, c2, weight) ->
        if (!isSameParent(c1, c2)){
            unionParent(c1, c2)
            answer += weight
        }
    }

    println(answer)
}