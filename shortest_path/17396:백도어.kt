package shortest_path

import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun String.toBoolean(): Boolean = this == "1"

data class Map(val node: Int, val time: Long)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val canSee = readLine().split(" ").map { it.toBoolean() }.toBooleanArray()
    val adjList = Array(n) { ArrayList<Map>() }
    val timeArray = LongArray(n) { Long.MAX_VALUE }

    repeat(m) {
        val (n1, n2, time) = readLine().split(" ").map { it.toInt() }
        adjList[n1].add(Map(node = n2, time = time.toLong()))
        adjList[n2].add(Map(node = n1, time = time.toLong()))
    }

    val pq = PriorityQueue<Map>(compareBy { it.time })
    pq.add(Map(node = 0, time = 0L))
    timeArray[0] = 0

    while (pq.isNotEmpty()) {
        val (curNode, curTime) = pq.poll()

        if (curNode == n - 1) {
            println(curTime)
            return@with
        }

        if (timeArray[curNode] < curTime) continue

        for (adjNode in adjList[curNode]) {
            val newTime = adjNode.time + curTime
            if (!canSee[adjNode.node] || adjNode.node == n - 1) {
                if (timeArray[adjNode.node] > newTime) {
                    timeArray[adjNode.node] = newTime
                    pq.add(adjNode.copy(time = newTime))
                }
            }
        }
    }

    println(-1)
}