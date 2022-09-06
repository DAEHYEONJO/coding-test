package backtracking

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){

    val t = readLine().toInt()
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        repeat(t){

            val n = readLine().toInt()
            val a = IntArray(1) + readLine().trim().split(" ").map{ it.toInt() }.toIntArray()

            val degrees = IntArray(n+1)

            for(i in 1 until n+1){
                degrees[a[i]]++
            }

            val q = ArrayDeque<Int>()

            for (i in 1 until n+1){
                if (degrees[i]==0) q.add(i)
            }

            var ans = 0
            while (q.isNotEmpty()){
                val curStudent = q.pollFirst()
                degrees[a[curStudent]]--
                if (degrees[a[curStudent]]==0) q.add(a[curStudent])
                ans++
            }

            w.write("${ans}\n")
        }
        w.flush()
    }

}