package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val adjList = Array<LinkedList<Int>>(n + 1) { LinkedList() }
    val tree = IntArray(n + 1) { i -> i }
    repeat(n - 1) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        adjList[a].add(b)
        adjList[b].add(a)
    }
    val visited = BooleanArray(n + 1) { false }.apply { this[1] = true }
    val queue = ArrayDeque<Int>().apply { add(1) }
    while (queue.isNotEmpty()) {
        val node = queue.pollFirst()
        adjList[node].forEach {
            if (!visited[it]) {
                tree[it] = node
                queue.add(it)
                visited[it] = true
            }
        }
    }
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        for (i in 2 until tree.size) {
            w.write("${tree[i]}\n")
        }
        w.flush()
    }
    this.close()
}