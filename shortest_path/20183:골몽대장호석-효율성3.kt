package shortest_path

import java.io.*
import java.util.*

data class GolMok2(val node: Int, val weight: Long, var curMoney: Long = 0L)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m, a, b, c) = readLine().split(" ").map { it.toLong() }
    val adjList = Array(n.toInt() + 1) { ArrayList<GolMok2>() }

    repeat(m.toInt()) {
        val (n1, n2, weight) = readLine().split(" ").map { it.toInt() }
        adjList[n1].add(GolMok2(n2, weight.toLong()))
        adjList[n2].add(GolMok2(n1, weight.toLong()))
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        fun dijkstra(pathMaxMoney: Long): Long {
            val moneyArray = LongArray(n.toInt() + 1) { Long.MAX_VALUE }
            val pq = PriorityQueue<GolMok2>(compareBy { it.weight }).apply {
                add(GolMok2(a.toInt(), 0L, c))
            }
            var maxMoney = 0L
            while(pq.isNotEmpty()){
                val (curNode, curWeight, curMoney) = pq.poll()

                if (curNode == b.toInt()){
                    return maxMoney
                }

                if (moneyArray[curNode] < curWeight) continue

                for (adjNode in adjList[curNode]){
                    val newWeight = curWeight + adjNode.weight
                    val canGoByCurMoney = curMoney >= adjNode.weight
                    val canGoByPathMaxMoney = pathMaxMoney >= adjNode.weight
                    if (moneyArray[adjNode.node] > newWeight && canGoByCurMoney && canGoByPathMaxMoney){
                        moneyArray[adjNode.node] = newWeight
                        val newCurMoney = curMoney - adjNode.weight
                        maxMoney = maxOf(maxMoney,adjNode.weight)
                        pq.add(adjNode.copy(weight = newWeight, curMoney = newCurMoney))
                    }
                }
            }

            return Long.MAX_VALUE
        }

        var sM = 1L
        var eM = 10e9.toLong()
        var minMoney = Long.MAX_VALUE
        while(sM<=eM){
            val mM = (sM + eM) / 2
            val result = dijkstra(mM)
            minMoney = minOf(minMoney, result)
            if (result == Long.MAX_VALUE){
                sM = mM + 1
            }else{
                eM = mM - 1
            }
        }
        w.write("${if (minMoney == Long.MAX_VALUE) -1 else minMoney}")
        w.flush()
        w.close()
    }

    close()

}