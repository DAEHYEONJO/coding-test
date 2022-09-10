package mst

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val parents = IntArray(n + 1) { it }
    val nodeSet = HashSet<Int>()

    fun getParent(a: Int): Int {
        if (parents[a] == a) return a
        parents[a] = getParent(parents[a])
        return parents[a]
    }

    fun unionParents(a: Int, b: Int) {
        val pA = getParent(a)
        val pB = getParent(b)
        if (pA > pB) {
            parents[pA] = pB
        } else {
            parents[pB] = pA
        }
    }

    fun isParentsSame(a: Int, b: Int) = (getParent(a) == getParent(b))

    repeat(m) {
        val (n1, n2) = readLine().split(" ").map { it.toInt() }
        unionParents(n1, n2)
        with(nodeSet) {
            add(n1)
            add(n2)
        }
    }

    val weightArray = ArrayList<Triple<Int, Int, Int>>().apply {
        repeat(n) { row ->
            readLine().split(" ").map { it.toInt() }.forEachIndexed { col, weight ->
                if (row != 0 && row < col) {
                    add(Triple(weight, row + 1, col + 1))
                }
            }
        }
    }.sortedBy { it.first }

    var ansWeight = 0
    var ansCount = 0
    val ansNodeArray = ArrayList<Pair<Int, Int>>()

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        if (nodeSet.size == n) {
            w.write("0 0")
        } else {
            weightArray.forEach { (weight, n1, n2) ->
                if (!isParentsSame(n1, n2)) {
                    unionParents(n1, n2)
                    ansNodeArray.add(Pair(n1, n2))
                    ansWeight += weight
                    ansCount++
                }
            }
            w.write("$ansWeight $ansCount\n")
            ansNodeArray.forEach {
                w.write("${it.first} ${it.second}\n")
            }
        }
        w.flush()
    }
    close()
}