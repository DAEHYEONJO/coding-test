package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {


    BufferedWriter(OutputStreamWriter(System.`out`)).use { bw ->
        val t = readLine().toInt()
        repeat(t) {

            val (n, k) = readLine().trim().split(" ").map { it.toInt() }
            val times = IntArray(n + 1) { 0 }
            val stTimes = StringTokenizer(readLine())
            val q = ArrayDeque<Int>()
            val adjList = Array(n + 1) { LinkedList<Int>() }
            val degrees = IntArray(n + 1) { 0 }
            val dp = IntArray(n + 1) { 0 }

            repeat(n) { i ->
                times[i + 1] = stTimes.nextToken().toInt()
            }

            repeat(k) { i ->
                val (src, dst) = readLine().trim().split(" ").map { it.toInt() }
                adjList[src].add(dst)
                degrees[dst] += 1
            }

            val w = readLine().toInt()

            for (i in 1 until n + 1) {
                if (degrees[i] == 0) {
                    q.add(i)
                    dp[i] = times[i]
                }
            }

            while (q.isNotEmpty()) {
                val node = q.pollFirst()
                for (adjNode in adjList[node]) {
                    dp[adjNode] = dp[adjNode].coerceAtLeast(dp[node] + times[adjNode])
                    if(--degrees[adjNode] == 0){
                        q.add(adjNode)
                    }
                }
                if (node == w){
                    break
                }
            }

            bw.write("${dp[w]}\n")

        }
        bw.flush()
    }

}