package graph

import java.io.*

val answerSet = HashSet<Int>()
lateinit var adjList: Array<ArrayList<Int>>

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    adjList = Array(n + 1) { ArrayList() }

    repeat(n) { parent ->
        adjList[readLine().toInt()].add(parent + 1)
    }

    for (i in 1 until n + 1) {
        dfs(i, HashSet())
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        w.write("${answerSet.size}\n")
        answerSet.sorted().forEach {
            w.write("$it\n")
        }
        w.flush()
    }
    close()
}

fun dfs(node: Int, nodeSet: HashSet<Int>) {
    if (nodeSet.contains(node)) {
        answerSet.addAll(nodeSet)
        return
    }

    nodeSet.add(node)

    for (adjNode in adjList[node]) {
        dfs(adjNode, nodeSet.clone() as HashSet<Int>)
    }
    return
}