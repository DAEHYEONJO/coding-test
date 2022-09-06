package shortest_path

import java.io.*
import java.util.*
import kotlin.collections.ArrayList

data class Way(val node: Int, var weight: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val adjList = Array(n + 1) { ArrayList<Way>() }

    repeat(m) {
        val (src, dst, weight) = readLine().split(" ").map { it.toInt() }
        adjList[src].add(Way(node = dst, weight = weight))
        adjList[dst].add(Way(node = src, weight = weight))
    }

    val path = Array(n + 1) { IntArray(n + 1){Int.MAX_VALUE} }
    val firstNodes = Array(n + 1) { IntArray(n + 1) }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        fun calcShortestPath(src: Int){

            val pq = PriorityQueue<Way>(compareBy { it.weight })
            // 시작노드 넣기
            pq.add(Way(src, 0))


            while(pq.isNotEmpty()){
                val(curNode, curNodeWeight) = pq.poll()
                // src -> curNode 로 가는데 이미 누군가 방문해서 weight를 갱신해 놨으면 Pass
                if (path[src][curNode] < curNodeWeight) continue
                path[src][curNode] = curNodeWeight
                for (adjNode in adjList[curNode]){
                    val newNodeWeight = adjNode.weight + curNodeWeight
                    // src 에서 부터 curNode 와 인접한 adjNode.node 로 가는데
                    // curNode 를 거쳐서 adjNode.node 로 가는 비용이 더 작다면 갱신해주기
                    if (path[src][adjNode.node] > newNodeWeight){
                        path[src][adjNode.node] = newNodeWeight
                        // src -> curNode 로갈때 첫번째로 거치는 노드가 갱신이 되어있지 않다면
                        // curNode 는 src 와 인접한 노드이므로 adjNode.node 로 초기화 해주기
                        if (firstNodes[src][curNode]==0){
                            firstNodes[src][adjNode.node] = adjNode.node
                        }else{ // 이미 0이 아닌 다른수가 들어있다면 adjNode.node 는 src 와 인접한 노드가 아니다.
                            // 따라서 처음에 초기화 해줬던 임의의 node 값을 집어넣어 준다. -> curNode 까지 오는 길에 이미 초기화가 이뤄졌을 것이다.
                            firstNodes[src][adjNode.node] = firstNodes[src][curNode]
                        }
                        pq.add(adjNode.copy(weight = newNodeWeight))
                    }
                }
            }

        }

        for (src in 1..n) {
            calcShortestPath(src)
        }

        for (i in 1 .. n){
            for(j in 1 .. n){
                if (i==j){
                    w.write("- ")
                    continue
                }
                w.write("${firstNodes[i][j]} ")
            }
            w.write("\n")
            w.flush()
        }
    }
}