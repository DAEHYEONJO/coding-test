package codeforces

import java.io.*
import java.util.*

data class Node(
    var parent: Int = 0, var depth: Int = 0, var childs: Int = 0
) // depth: root 까지 거리,

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().trim().split(" ").map { it.toInt() }
    val adjList = Array(n + 1) { LinkedList<Int>() }
    val nodeInfo = Array(n + 1) { Node() }

    //문제를 풀기 위해서 각 node 로 부터, root 까지 거리를 알아야 하며, node 로 부터 밑에 child 수도 알아야 한다.

    repeat(n - 1) {
        val (n1, n2) = readLine().trim().split(" ").map { it.toInt() }
        adjList[n1].add(n2)
        adjList[n2].add(n1)
    }


    fun dfs(curNode: Int, depth: Int, parent: Int) {
        nodeInfo[curNode].run {
            this.depth = depth
            this.parent = parent
        }
        for(adjNode in adjList[curNode]) {
            if (adjNode!=parent) {
                nodeInfo[curNode].childs++
                dfs(adjNode, depth+1, curNode)
            }
        }
    }

    fun dfs2(curNode: Int, parent:Int): Int{
        for(adjNode in adjList[curNode]){
            if (adjNode != parent){
                nodeInfo[curNode].childs += dfs2(adjNode, curNode)
            }
        }
        return nodeInfo[curNode].childs
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w->
        dfs(1,0, 0)
        dfs2(1,0)
        var ans = 0L
        val result = IntArray(n){nodeInfo[it+1].depth - nodeInfo[it+1].childs}.sortedArrayDescending()
        repeat(k){
            ans+=result[it]
        }
        w.write("$ans")
        w.flush()
    }
}