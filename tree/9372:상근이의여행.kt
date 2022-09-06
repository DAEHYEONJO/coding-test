package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w->
        val t = readLine().toInt()
        repeat(t) {
            val (n, m) = readLine().trim().split(" ").map { it.toInt() }
            val adjList = Array<LinkedList<Int>>(n+1){LinkedList()}
            repeat(m) {
                val (a, b) = readLine().trim().split(" ").map { it.toInt() }
                adjList[a].add(b)
                adjList[b].add(a)
            }

            val queue = ArrayDeque<Int>()
            queue.add(1)
            val visited = HashSet<Int>(n)
            visited.add(1)
            var result = 0
            while(queue.isNotEmpty()){
                val node = queue.poll()
                if (!visited.contains(node)) {
                    result+=1
                    visited.add(node)
                    if (visited.size == n) break
                }
                adjList[node].forEach{
                    if (!visited.contains(it)){
                        queue.add(it)
                    }
                }
            }

            w.write("$result\n")
        }
        w.flush()
    }
}