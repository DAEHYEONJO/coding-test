package codeforces

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    data class Node(
        var adjList: LinkedList<Int> = LinkedList<Int>(),
        var d: LinkedList<Int> = LinkedList<Int>(),
        var cd: LinkedList<Int> = LinkedList<Int>(),
        var dSum: Int = 0,
        var ans: Int = 0
    )
    // d = 1 when x to y
    // d = 0 when y to x
    // cd -> 현재 노드의 i 번째 child 노드로부터 나까지 화살표를 받는 개수
    val n = readLine().toInt()
    val nodes = Array(n + 1) {
        Node()
    }
    repeat(n - 1) {
        val (src, dst) = readLine().trim().split(" ").map { it.toInt() }
        nodes[src].run {
            adjList.add(dst)
            d.add(0)
            cd.add(0)
        }
        nodes[dst].run {
            adjList.add(src)
            d.add(1)
            cd.add(0)
        }
    }

    fun dfs1(node: Int, parentNode: Int): Int {
        for (i in nodes[node].adjList.indices) {
            if (nodes[node].adjList[i] != parentNode) {
                nodes[node].cd[i] += dfs1(nodes[node].adjList[i], node)
            }
        }

        var sum = 0

        for (i in nodes[node].adjList.indices) {
            if (nodes[node].adjList[i] != parentNode) {
                sum += nodes[node].d[i] + nodes[node].cd[i]
            }
        }
        nodes[node].dSum = sum

        return sum
    }

    dfs1(1, 0) // node 기준으로 밑에서 나한테 화살표 몇갞쏘는지 cd[i]에 계산됨 (bottom -> up)
    // 하지만 나로부터 parents 로 나가는 방향의 화살표가 고려가 되지 않았음
    // 따라서 top -> down으로 parent에서 child로 가는 화살표가 꽂히고 있다면
    // child의 dSum -=1 해줘야함.

    fun dfs2(node: Int, parentNode: Int, parentSum: Int, dr: Int) {
        if (parentNode == 0) {
            nodes[node].ans = nodes[node].dSum
        } else {
            if (dr == 0) {
                nodes[node].ans = nodes[parentNode].ans + 1
            } else {
                nodes[node].ans = nodes[parentNode].ans - 1
            }
        }
        for (i in nodes[node].adjList.indices) {
            if (nodes[node].adjList[i] != parentNode) {
                dfs2(nodes[node].adjList[i],
                    node,
                    nodes[node].dSum,
                    nodes[node].d[i],
                )
            }
        }
        return
    }
    dfs2(1, 0, 0, 0)

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        nodes.forEach{
            w.write("$it\n")
        }
        w.write("\n")
        var min = Int.MAX_VALUE
        for(i in 1 until n+1){
            min = min.coerceAtMost(nodes[i].ans)
        }
        w.write("$min\n")
        for(i in 1 until n+1){
            if (nodes[i].ans == min)
                w.write("$i ")
        }
        w.flush()

    }
}