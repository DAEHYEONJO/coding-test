package mst

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (v, e) = readLine().split(" ").map { it.toInt() }
    val nodeList = Array(v+1){ArrayList<Pair<Long, Int>>()}
    repeat(e){
        val (n1, n2, w) = readLine().split(" ").map { it.toInt() }
        nodeList[n1].add(Pair(w.toLong(), n2))
        nodeList[n2].add(Pair(w.toLong(), n1))
    }

    val visited = BooleanArray(v+1)
    visited[1] = true

    val pq = PriorityQueue<Pair<Long, Int>>(compareBy {
        it.first
    }).apply {
        addAll(nodeList[1])
    }

    var answer = 0L

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        while (pq.isNotEmpty()) {

            val (curWeight, curNode) = pq.poll()

            if (visited[curNode]) continue
            visited[curNode] = true
            answer += curWeight

            for (adjNode in nodeList[curNode]){
                if (!visited[adjNode.second]){
                    pq.add(adjNode)
                }
            }

        }

        w.write("$answer")
        w.flush()
    }
}