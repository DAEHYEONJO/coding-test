package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){


    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val t = readLine().toInt()
        val dp = Array(11){LongArray(2001)}
        for (i in 1 until 2001){
            dp[1][i] = 1L
        }

        for(i in 1 until 10){
            for(j in 1 until 2001){
                if (dp[i][j]==0L) continue
                var overDouble = 2*j
                while (overDouble < 2001){
                    dp[i+1][overDouble++]+=dp[i][j]
                }
            }
        }
        repeat(t){
            val (n, m) = readLine().trim().split(" ").map{ it.toInt() }
            var sum = 0L
            for(i in 1 until m+1){
                sum+=dp[n][i]
            }
            w.write("$sum\n")
        }
        w.flush()
    }
}