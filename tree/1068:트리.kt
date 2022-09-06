package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val tree = readLine().split(" ").map{it.toInt()}.toIntArray()
    val adjList = Array<LinkedList<Int>>(n){LinkedList()}
    var root = -1
    val visited = BooleanArray(n){false}
    val queue = ArrayDeque<Int>()
    tree.forEachIndexed{ index, node ->
        if (node!=-1){
            adjList[index].add(node)
            adjList[node].add(index)
        }else{
            root = index
        }
    }
    val removeNode = readLine().toInt()
    if (removeNode == root) {
        println(0)
        return@main
    }

    visited[root] = true
    visited[removeNode] = true
    queue.add(root)

    var leafCount = 0
    val leafList = ArrayList<Int>()
    fun bfs(){
        while (queue.isNotEmpty()){
            val node = queue.pollFirst()
            val beforeCount = queue.size
            //println("popNode: $node beforQueue: ${queue}")
            for (adjNode in adjList[node]){
                if (!visited[adjNode]){
                    visited[adjNode] = true
                    queue.add(adjNode)
                }
            }
            //println("popNode: $node afterQueue: ${queue}")
            if (beforeCount == queue.size){
                leafCount++
                leafList.add(node)
            }
        }
    }
    bfs()
    println(leafCount)
    //println(leafList.joinToString(truncated = ","))
}
