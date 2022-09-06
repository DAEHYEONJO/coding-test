package graph

import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.out)).use{
        val (n, m) = readLine().split(" ").map{it.toInt()}
        val graph = Array(n){ArrayList<Int>()}
        val indegree = IntArray(n){0}
        val priorityQueue = PriorityQueue<Int>(n)
        repeat(m){
            val (src, dst) = readLine().split(" ").map{it.toInt()}
            graph[src-1].add(dst-1)
            indegree[dst-1]++
        }

        indegree.forEachIndexed { index, value ->
            if(value == 0) priorityQueue.add(index)
        }
        while (priorityQueue.isNotEmpty()){
            val problem = priorityQueue.poll()
            it.write("${problem+1} ")
            //problem이 향하는 node의 indegree 1씩 감소 시켜주기
            graph[problem].forEach {
                indegree[it]--
                if (indegree[it] == 0) priorityQueue.add(it)
            }
        }
        it.flush()
    }
}