package tree

import java.io.*
import java.util.*
import kotlin.math.max

//tree의 지름에 해당하는 양 끝 점은 특정 node 에서 출발해서 가장 멀리 떨어져 있는 점 중 하나일 것이다.
//dfs 호출을 최적화 하기 위하여 정말 아무노드 에서 dfs를 출발하는 것이 아닌 root node 에서 출발하여
//가장 멀리 떨어진 한 점을 구한다.
//그리고 그 점으로부터 가장 멀리 떨어져 있는 점 까지의 weight 합이 tree의 지름이 될 것이다.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val adjList = Array<LinkedList<Pair<Int, Int>>>(n+1){ LinkedList() }//[src] to (dst, weight)

    repeat(n-1){
        val (src, dst, weight) = readLine().split(" ").map{ it.toInt() }
        adjList[src].add(Pair(dst, weight))
        adjList[dst].add(Pair(src, weight))
    }
    var maxNodeWeight = 0
    var maxNode = 0
    fun dfs(visited: BooleanArray, node: Int, weight: Int){
        if (visited[node]) return
        visited[node] = true
        if (maxNodeWeight < weight){
            maxNodeWeight = weight
            maxNode = node
        }
        for ((n, w) in adjList[node]){
            dfs(visited, n, w+weight)
        }
    }
    val visited = BooleanArray(n+1){false}
    dfs(visited, 1, 0)
    maxNodeWeight = 0
    dfs(BooleanArray(n+1){false}, maxNode, maxNodeWeight)
    println(maxNodeWeight)


}