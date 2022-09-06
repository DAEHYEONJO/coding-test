package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val tree = Array(n+1){ArrayList<Pair<Int,Int>>()}
    val degrees = IntArray(n+1)

    repeat(n-1){
        val (p, c, w) = readLine().trim().split(" ").map{ it.toInt() }
        tree[p].add(Pair(c, w))
        tree[c].add(Pair(p,w))
        degrees[p]++
        degrees[c]++
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        var weightSum = 0
        fun dfs(visited: BooleanArray, node: Int, sum: Int){
            visited[node] = true
            if (weightSum < sum){
                weightSum = sum
            }
            for((adjNode, weight) in tree[node]){
                if (!visited[adjNode]){
                    dfs(visited, adjNode, sum+weight)
                }
            }
            return
        }
        var ans = 0
        degrees.forEachIndexed{ node, degree ->
            if (degree==1){
                weightSum = 0
                dfs(BooleanArray(n+1),node,0)
                ans = maxOf(ans, weightSum)
            }
        }
        w.write("$ans")
        w.flush()
    }
}