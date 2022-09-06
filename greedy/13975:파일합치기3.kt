package greedy

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val t = readLine().toInt()
        repeat(t){
            val n = readLine().toInt()
            val q = PriorityQueue<Long>()
            var ans = 0L
            val st = StringTokenizer(readLine().trim())
            repeat(n){
                q.add(st.nextToken().toLong())
            }
            while (q.size!=1){
                val c1 = q.poll()
                val c2 = q.poll()
                q.add(c1+c2)
                ans+=(c1+c2)
            }
            w.write("${ans}\n")
        }
        w.flush()
        this.close()
    }

}