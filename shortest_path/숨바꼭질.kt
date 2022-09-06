package shortest_path

import java.io.*
import java.util.*


fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n,m) = readLine().split(" ").map{it.toInt()}
    val graph = Array(n){LinkedList<Int>()}
    val table = Array(n){Integer.MAX_VALUE}
    repeat(m){
        val (n1, n2) = readLine().split(" ").map{it.toInt()}
        graph[n1-1].add(n2-1)
        graph[n2-1].add(n1-1)
    }

    val qp = PriorityQueue<Pair<Int, Int>>(kotlin.Comparator { o1, o2 ->
        o1.first - o2.first // 앞 - 뒤 => 양수면 자리바꾸기 => 오름차순 정렬
    })
    qp.add(Pair(0,0))//weight, node
    table[0] = 0
    while (qp.isNotEmpty()){
        val node = qp.poll()
        if (table[node.second] < node.first) continue
        val nodes = graph[node.second]
        for(dst in nodes){
            if (table[dst] > node.first+1){
                qp.add(Pair(node.first + 1, dst))
                table[dst] = node.first + 1
            }
        }
    }
    val maxValue = table.maxOrNull()
    val minIndex = table.indexOf(maxValue)
    val maxValueCount = table.count { it == maxValue }
    println("${minIndex+1} $maxValue $maxValueCount")
}