package graph

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val adjList = Array(n + 1) { ArrayList<Int>() }
    val parents = IntArray(n + 1) { it }

    fun getParent(a: Int): Int {
        if (parents[a] == a) return a
        parents[a] = getParent(parents[a])
        return parents[a]
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

    fun isSameParents(a: Int, b: Int): Boolean = (getParent(a) == getParent(b))

    // (i, j)가 연결되어 있는지 여부
    // i <-> j => undirected graph
    repeat(n) { row ->
        readLine().split(" ").map { it.toInt() }.forEachIndexed { col, connected ->
            if (row != col && connected == 1) {
                adjList[row + 1].add(col + 1)
                if (!isSameParents(row + 1, col + 1)) {
                    unionParents(row + 1, col + 1)
                }
            }
        }
    }

    // m 개의 원소가 존재함 차례대로 방문해야함
    val travelNode = readLine().split(" ").map { it.toInt() }.toMutableList()

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        while (travelNode.isNotEmpty()){
            val curTravelSrcNode = travelNode.removeFirst()
            if (travelNode.isEmpty()){
                w.write("YES")
                return@with
            }else{
                val curTravelDstNode = travelNode.first()
                if (!isSameParents(curTravelSrcNode, curTravelDstNode)){
                    break
                }
            }
        }
        w.write("NO")
    }

}

