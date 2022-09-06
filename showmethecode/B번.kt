package showmethecode

import java.io.*
import java.math.BigInteger
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, q) = readLine().trim().split(" ").map { it.toInt() }
    val a = LongArray(n + 1) { 0 }
    val st = StringTokenizer(readLine())

    repeat(n) {
        a[it + 1] = st.nextToken().toLong()
    }
    val adjList = Array(n + 1) {
        LinkedList<Int>()
    }
    repeat(n - 1) {
        val (src, dst) = readLine().trim().split(" ").map { it.toInt() }
        adjList[src].add(dst)
        adjList[dst].add(src)
    }


    val m = BigInteger("1000000007")

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        repeat(q) {
            val (src, dst) = readLine().trim().split(" ").map { it.toInt() }
            if (src == dst){
                w.write("${a[src]}\n")
            }else{
                val visited = BooleanArray(n+1){false}
                visited[src] = true
                val queue = ArrayDeque<Pair<Int, String>>()
                queue.add(Pair(src, a[src].toString()))
                while(queue.isNotEmpty()){
                    val (cNode, str) = queue.pollFirst()

                    for(adjNode in adjList[cNode]){
                        if (!visited[adjNode]) {
                            val newStr = "$str${a[adjNode]}"
                            if (adjNode == dst) {
                                w.write("${BigInteger(newStr).mod(m)}\n")
                                break
                            }
                            queue.add(Pair(adjNode,newStr))
                            visited[adjNode] = true
                        }
                    }
                }

            }
        }
        w.flush()
    }
}