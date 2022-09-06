package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val adjList = Array(n+1){LinkedList<Int>()}
    val degrees = IntArray(n + 1) { 0 }
    val ans = IntArray(n) { 0 }
    repeat(m){
        val st = StringTokenizer(readLine())
        val src = st.nextToken().toInt()
        val dst = st.nextToken().toInt()
        adjList[src].add(dst)
        degrees[dst]+=1
    }

    val queue = ArrayDeque<Pair<Int, Int>>()
    val semester = 1

    for(i in 1 until n+1){
        if(degrees[i] == 0){
            queue.add(Pair(i, semester))
        }
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        while(queue.isNotEmpty()){
            val (preNum, preSemester) = queue.pollFirst()
            ans[preNum-1] = preSemester
            for (node in adjList[preNum]){
                degrees[node]-=1
                if (degrees[node]==0){
                    queue.add(Pair(node, preSemester+1))
                }
            }
        }
        w.write(ans.joinToString(separator = " "))
        w.flush()
    }
}