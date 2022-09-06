package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        var n : String = ""
        while(readLine().also { n = it }!="0"){
            val N = n.toInt()
            val adjList = Array<LinkedList<Int>>(N+1){ LinkedList() }
            val keyBalls = IntArray(N+1)
            repeat(N){
                val st = StringTokenizer(readLine())
                val key = st.nextToken().toInt()
                val balls = st.nextToken().toInt()
                keyBalls[key] = balls
                val childCount = st.nextToken().toInt()
                if (childCount!=0){
                    repeat(childCount){
                        adjList[key].add(st.nextToken().toInt())
                    }
                }
            }
            w.write("${adjList.contentDeepToString()}\n")
            w.write("${keyBalls.contentToString()}\n")
            w.write("\n")
        }
    }
}