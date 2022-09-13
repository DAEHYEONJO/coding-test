package shortest_path

import java.io.*
import java.util.*

data class Network(val node: Int, var price: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, p, k) = readLine().split(" ").map { it.toInt() }
    val adjList = Array(n + 1) { ArrayList<Network>() }

    repeat(p) {
        val (n1, n2, price) = readLine().split(" ").map { it.toInt() }
        adjList[n1].add(Network(node = n2, price = price))
        adjList[n2].add(Network(node = n1, price = price))
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        fun dijkstra(pathMaxPrice: Int): Boolean {
            val pq = PriorityQueue<Network>(compareBy { it.price }).apply {
                add(Network(node = 1, price = 0))
            }
            val priceArray = IntArray(n+1){Int.MAX_VALUE}
            priceArray[1] = 0

            while(pq.isNotEmpty()){
                val (curNode, curPrice) = pq.poll()

                if (priceArray[curNode] < curPrice) continue

                for (adjNode in adjList[curNode]){
                    val weight = if (adjNode.price <= pathMaxPrice) 0 else 1
                    val newPrice = curPrice + weight
                    if (priceArray[adjNode.node] <= newPrice) continue
                    priceArray[adjNode.node] = newPrice
                    pq.add(adjNode.copy(price = newPrice))
                }
            }

            return priceArray[n] <= k
        }

        var s = 0
        var e = 1_000_001
        while (s <= e){
            val m = s + (e - s)/2
            val result = dijkstra(m)
            if (result) {
                e = m - 1
            }else{
                s = m + 1
            }
        }

        if (s == 1_000_002){
            w.write("-1")
        }else{
            w.write("$s")
        }

        w.flush()
    }

}