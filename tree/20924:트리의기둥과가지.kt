package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, root) = readLine().split(" ").map { it.toInt() }
    val adjList = Array<LinkedList<Pair<Int, Int>>>(n + 1) { LinkedList() }
    repeat(n - 1) {
        val (n1, n2, weight) = readLine().split(" ").map { it.toInt() }
        adjList[n1].add(Pair(n2, weight))
        adjList[n2].add(Pair(n1, weight))
    }

    val queue = ArrayDeque<Int>()
    var isLeafGiga = true
    var gigaNode = -1
    val visited = BooleanArray(n + 1) { false }.apply { this[root] = true }
    queue.add(root)
    var barLength = 0
    while (queue.isNotEmpty()) {
        val curNode = queue.poll()
        val notVisitedNodes = adjList[curNode].filter { !visited[it.first] }
        when {
            notVisitedNodes.size >= 2 -> {
                isLeafGiga = false;
                gigaNode = curNode;
                break
            }
            notVisitedNodes.size == 1 -> {
                visited[notVisitedNodes.first().first] = true
                barLength += notVisitedNodes.first().second
                queue.add(notVisitedNodes.first().first)
            }
            notVisitedNodes.isEmpty() -> {
                gigaNode = curNode
            }
        }
    }

    var longestBranch = 0
    if (!isLeafGiga) {
        fun dfs(node:Int, branchLength: Int){
            val notVisitedNodes = adjList[node].filter{!visited[it.first]}
            if (notVisitedNodes.isEmpty()){
                longestBranch = longestBranch.coerceAtLeast(branchLength)
                return
            }
            notVisitedNodes.forEach {
                visited[it.first] = true
                dfs(it.first, branchLength+it.second)
                visited[it.first] = false
            }
            return
        }
        dfs(gigaNode, 0)
    }
    println("$barLength $longestBranch")
}