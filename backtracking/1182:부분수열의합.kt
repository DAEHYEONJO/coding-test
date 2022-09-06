package backtracking

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, s) = readLine().trim().split(" ").map{ it.toInt() }
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    var ans = 0

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        fun dfs(depth: Int, start: Int, sum: Int, r: Int){
            if (depth == r){
                if (sum==s) ans++
                return
            }
            for (i in start until n){
                dfs(depth+1, i+1, sum+a[i], r)
            }
        }

        for (i in 1 until n+1){
            dfs(0,0,0,i)
        }
        w.write("${ans}\n")
    }

}