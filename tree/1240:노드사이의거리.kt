package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val(n, m) = readLine().split(" ").map{ it.toInt() }
        val adjList = HashMap<Int, LinkedList<Pair<Int,Int>>>()
        repeat(n-1){
            val (a, b, c) = readLine().split(" ").map{ it.toInt() }
            adjList.run{put(a, getOrDefault(a, LinkedList<Pair<Int, Int>>()).apply { add(Pair(b,c)) })}
            adjList.run{put(b, getOrDefault(b, LinkedList<Pair<Int, Int>>()).apply { add(Pair(a,c)) })}

        }
        repeat(m){
            val (a, b) = readLine().split(" ").map{ it.toInt() }
            val visited = HashSet<Int>()
            val queue = ArrayDeque<Pair<Int, Int>>().apply { addAll(adjList[a]!!) }
            var result = 0
            while(queue.isNotEmpty()){
                val (curNode, dist) = queue.poll()
                if (!visited.contains(curNode)){
                    if (curNode == b) {
                        w.write("$dist\n")
                        break
                    }
                    visited.add(curNode)
                    adjList[curNode]?.forEach{
                        queue.add(Pair(it.first, it.second+dist))
                    }
                }
            }
        }
        w.flush()
    }
}