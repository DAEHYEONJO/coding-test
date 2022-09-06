package graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, m) = readLine().split(" ").map{it.toInt()}
    val table = Array<Array<Int>>(n){Array<Int>(m){0}}
    repeat(n){ i->
        readLine().forEachIndexed { index, c ->
            table[i][index] = c.toString().toInt()
        }
    }

    val priorityQueue = PriorityQueue<Triple<Int,Int,Int>>(kotlin.Comparator { o1, o2 ->
        o1.first - o2.first
    })

    priorityQueue.add(Triple(1, 0, 0))
    table[0][0] = -1
    fun bfs(){
        val dy = intArrayOf(0, 1, 0, -1)
        val dx = intArrayOf(1, 0, -1, 0)
        while(priorityQueue.isNotEmpty()){
            val (count, y, x) = priorityQueue.poll()
            if ((y == n-1) && (x == m-1)){
                println(count)
                return
            }

            for (i in 0 until 4){
                val newY = y + dy[i]
                val newX = x + dx[i]
                if ((newY>=0) && (newY<=n-1) && (newX>=0) && (newX<=m-1) && (table[newY][newX]==1)){
                    table[newY][newX]=-1
                    priorityQueue.add(Triple(count+1, newY, newX))
                }
            }
        }
    }
    bfs()
}