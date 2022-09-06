package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val adjList = Array<LinkedList<Pair<Int,Int>>>(n+1){ LinkedList() }
    val degrees = IntArray(n+1){0}

    repeat(n-1){
        val (parent, child, weight) = readLine().split(" ").map{ it.toInt() }
        degrees[parent]++
        degrees[child]++
        adjList[parent].add(Pair(child, weight))
        adjList[child].add(Pair(parent, weight))
    }
    var result = 0
    fun dfs(visited: BooleanArray, node: Int, r: Int){
        //println("node: $node r: $r degree: ${degrees[node]} visited: ${visited[node]}")
        if (degrees[node]==1 && !visited[node]){
            result = result.coerceAtLeast(r)
            return
        }
        if (!visited[node]){
            for ((n, w) in adjList[node]){
                visited[node] = true
                dfs(visited, n, r+w)
                visited[node] = false
            }
        }else{
            return
        }
    }
    var answer = 0
    degrees.forEachIndexed { index, i ->
        if (i == 1){
            //println("index: $index")
            val visited = BooleanArray(n+1){false}
            visited[index] = true
            dfs(visited, adjList[index][0].first, adjList[index][0].second)
            answer = answer.coerceAtLeast(result)
        }
    }
    println(answer)



}

