package shortest_path

import java.io.*
import java.util.*

data class PartyInfo(val node: Int, val weight: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, m, x) = readLine().split(" ").map{ it.toInt() }
    val adjList = Array(n+1){ArrayList<PartyInfo>()}
    repeat(m){
        val (src, dst, weight) = readLine().split(" ").map{ it.toInt() }
        adjList[src].add(PartyInfo(node = dst, weight = weight))
    }

    fun getShortestPath(startNode: Int, dstNode: Int): Int{
        val costArr = IntArray(n+1){Int.MAX_VALUE}
        costArr[startNode] = 0

        val pq = PriorityQueue<PartyInfo>(compareBy { it.weight })
        pq.add(PartyInfo(node = startNode, weight = 0))


        while (pq.isNotEmpty()){
            val (curNode, curWeight) = pq.poll()

            // 도착하고자 하는 노드면 curWeight return
            if (curNode == dstNode){
                return curWeight
            }

            // 이미 누가 방문해서 최솟값을 갱신해 놨으면 탐색하지 않기
            if (costArr[curNode] < curWeight) continue

            // 나와 인접한 노드들 돌면서 나를 거치고, 인접노드로 갔을때 비용이 최소가 되는 애들이 있다면 pq에 넣어주기
            for (adjNode in adjList[curNode]){
                val newWeight = curWeight + adjNode.weight
                if (costArr[adjNode.node] > newWeight){
                    costArr[adjNode.node] = newWeight
                    pq.add(adjNode.copy(weight = newWeight))
                }
            }
        }
        // 여긴 도달할 수 없는 return문임. 입력자체가 파티장으로 가고 집으로 돌아올 수 있는 입력만 주어지기 때문
        return -1000000
    }

    var maxWeight = 0
    for (i in 1 until n+1){
        val goToPartyWeight = getShortestPath(i, x)
        val backToHomeWeight = getShortestPath(x, i)
        maxWeight = maxOf(maxWeight, goToPartyWeight + backToHomeWeight)
    }

    println(maxWeight)

}