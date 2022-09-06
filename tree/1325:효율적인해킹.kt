package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    //val adjList = Array(n+1) { LinkedList<Int>() }
    val adjList = Array(n+1) { ArrayList<Int>() }
    //val hacking = IntArray(n+1){ 0 }
    val hacking = IntArray(n+1)

    //4      1
    //    3
    //5      2

    repeat(m){
        st = StringTokenizer(readLine())
        val dst = st.nextToken().toInt()
        val src = st.nextToken().toInt()
        adjList[dst].add(src)
    }

    var maxValue = 0
    fun bfs(node: Int){
        val q = ArrayDeque<Int>()
        val visited = BooleanArray(n+1)
        visited[node] = true
        q.add(node)
        while(q.isNotEmpty()){
            val curNode= q.pollFirst()
            hacking[curNode] += 1
            maxValue = maxValue.coerceAtLeast(hacking[curNode])
            for(adjNode in adjList[curNode]){
                if (!visited[adjNode]){
                    visited[adjNode] = true
                    q.add(adjNode)
                }
            }
        }
    }

    for( i in 1 until n+1){
        bfs(i)
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        for(i in 1 until n+1){
            if (hacking[i] == maxValue){
                w.write("$i ")
            }
        }
        w.flush()
    }
}