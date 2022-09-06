package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val n = readLine().toInt()
        val adjList = Array<LinkedList<Int>>(n+1){ LinkedList() }

        repeat(n-1){
            val (n1, n2) = readLine().split(" ").map{ it.toInt() }
            adjList[n1].add(n2)
            adjList[n2].add(n1)
        }
        val q = readLine().toInt()
        repeat(q){
            val (t, k) = readLine().split(" ").map{ it.toInt() }
            when(t){
                1->{
                    if (adjList[k].size!=1){
                        w.write("yes\n")
                    }else{
                        w.write("no\n")
                    }
                }
                2->{
                    w.write("yes\n")
                }
            }
        }
        w.flush()
        this.close()
    }
}