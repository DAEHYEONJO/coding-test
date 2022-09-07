package shortest_path

import java.io.*
import java.util.*

data class GolMok(val node: Int, val weight: Long, var money: Long = 0L)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // n: 노드수 m: 엣지수 a: 시작노드, b: 도착노드, c: 가진 돈
    val (n, m, a, b, c) = readLine().split(" ").map { it.toInt() }
    val adjList = Array(n + 1) { ArrayList<GolMok>() }
    repeat(m) {
        val (n1, n2, money) = readLine().split(" ").map { it.toInt() }
        adjList[n1].add(GolMok(node = n2, weight = money.toLong()))
        adjList[n2].add(GolMok(node = n1, weight = money.toLong()))
    }

    fun dijkstra(maxMoney: Long): Long {
        var maxAnswer = 0L

        val moneyArray = LongArray(n + 1) { Long.MAX_VALUE }
        moneyArray[a] = 0L

        val pq = PriorityQueue<GolMok>(compareBy { it.weight }).apply {
            add(GolMok(a, 0L, c.toLong()))
        }

        while (pq.isNotEmpty()) {
            val (curNode, curWeight, curMoney) = pq.poll()
            if (curNode == b) {
                return maxAnswer
            }
            if (moneyArray[curNode] < curWeight) continue

            for (adjNode in adjList[curNode]) {
                val newWeight = curWeight + adjNode.weight
                val canGo = curMoney >= adjNode.weight
                if (moneyArray[adjNode.node] > newWeight && canGo && adjNode.weight <= maxMoney) {
                    val newMoney = curMoney - adjNode.weight
                    maxAnswer = maxOf(maxAnswer, adjNode.weight)
                    moneyArray[adjNode.node] = newWeight
                    pq.add(adjNode.copy(weight = newWeight, money = newMoney))
                }
            }

        }
        return Long.MAX_VALUE

    }

    var minMoney = Long.MAX_VALUE
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        for (i in 1 until 21) {
            minMoney = minOf(minMoney, dijkstra(i.toLong()))
        }
        w.write("${if (minMoney == Long.MAX_VALUE) -1 else minMoney}")
        w.flush()
        w.close()
    }
    close()
}