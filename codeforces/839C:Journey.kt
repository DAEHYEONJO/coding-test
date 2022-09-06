package codeforces

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val adjList = Array(100005) { LinkedList<Int>() }
    val visited = BooleanArray(100005) { false }
    repeat(n - 1) {
        val (n1, n2) = readLine().trim().split(" ").map { it.toInt() }
        adjList[n1].add(n2)
        adjList[n2].add(n1)
    }

    visited[1] = true
    var ans = 0.0

    fun dfs(node: Int, depth: Int, p: Double) {
        var childCount = 0

        adjList[node].forEach {
            if (!visited[it])
                childCount++
        }

        adjList[node].forEach {
            if (!visited[it]) {
                visited[it] = true
                dfs(it, depth + 1, p / childCount)
            }
        }

        if (childCount == 0) {
            ans += (depth * p)
        }
        return@dfs
    }
    dfs(1, 0, 1.0)
    println(ans)
}