package graph

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val adjListInOrder = Array(n + 1) { ArrayList<Int>() }
    val adjListReverseOrder = Array(n + 1) { ArrayList<Int>() }
    var answer = 0

    repeat(m) {
        val (small, big) = readLine().split(" ").map { it.toInt() }
        adjListInOrder[small].add(big)
        adjListReverseOrder[big].add(small)
    }

    for (i in 1 until n + 1) {
        // 내 아래, 내 위 node 개수 bfs로 파악
        val downNodes = bfs(i, adjListInOrder)
        val upNodes = bfs(i, adjListReverseOrder)
        // 내 위로, 내 아래로 합한게 나 빼고 n - 1 개라면 등수가 확실히 정해진다.
        if (downNodes + upNodes == n - 1) {
            answer++
        }
    }
    println(answer)
    close()
}

fun bfs(startNode: Int, adjList: Array<ArrayList<Int>>): Int {
    val nodeSet = mutableSetOf<Int>()
    // 방문배열 안넣으면 들어갔던게 또 들어가서 메모리 초과 발생
    val visited = BooleanArray(adjList.size)
    visited[startNode] = true
    val q = ArrayDeque<Int>().apply {
        add(startNode)
    }

    while (q.isNotEmpty()) {
        val curNode = q.pollFirst()
        for (adjNode in adjList[curNode]) {
            if (visited[adjNode]) continue
            visited[adjNode] = true
            nodeSet.add(adjNode)
            q.addLast(adjNode)
        }
    }
    return nodeSet.size
}

