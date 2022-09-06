package graph

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(mePos, dsPos) = readLine().trim().split(" ").map{it.toInt()}
    val visited = BooleanArray(100001)
    val pq = PriorityQueue<Pair<Int,Int>>(compareBy { it.first }) //초, 위치
    pq.add(Pair(0, mePos))

    fun isValid(x: Int): Boolean = x in (0 until 100001) && !visited[x]

    while (pq.isNotEmpty()){
        val(time, pos) = pq.poll()
        if(!visited[pos]){
            // 이미 pq에 들어간 position 도 time 값이 갱신될 수 있도록 여기서 방문처리
            visited[pos] = true
            if (pos == dsPos){
                println(time)
                return@with
            }
            val left = pos-1
            val right = pos+1
            val two = pos shl 1
            if (isValid(left)){
                pq.add(Pair(time+1, left))
            }
            if (isValid(right)){
                pq.add(Pair(time+1, right))
            }
            if (isValid(two)){
                pq.add(Pair(time, two))
            }
        }
    }
}

