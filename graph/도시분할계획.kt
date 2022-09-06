package graph

import java.io.*
import java.util.*
import kotlin.math.max

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, m) = readLine().split(" ").map{it.toInt()}
    val graph = Array<LinkedList<Pair<Int, Int>>>(n+1){LinkedList<Pair<Int,Int>>()}
    repeat(m){
        val (src, dst, weight) = readLine().split(" ").map{it.toInt()}

        graph[src].add(Pair(weight, dst))
        graph[dst].add(Pair(weight, src))
    }
    val visited = BooleanArray(n+1){false}
    visited[1] = true
    val priorityQueue = PriorityQueue<Pair<Int,Int>>(kotlin.Comparator { o1, o2 ->
        o1.first - o2.first
    })
    graph[1].forEach {
        priorityQueue.add(it)
    }
    var result = 0
    var maxWeight = 0
    var count = 0
    while (priorityQueue.isNotEmpty()){

        val (weight, dst) = priorityQueue.poll()
        if (!visited[dst]){
            count++
            result+=weight
            maxWeight = max(weight, maxWeight)
            if (count == (n-1)) break
            visited[dst] = true
            val adjNodes = graph[dst]
            adjNodes.forEach {
                if (!visited[it.second]){
                    priorityQueue.add(it)
                }
            }
        }
    }
    println(result-maxWeight)
}