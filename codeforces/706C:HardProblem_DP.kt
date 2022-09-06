package codeforces

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt() // 2<=n<=100 000
    val c = readLine().trim().split(" ").map { it.toLong() }.toLongArray()

    val s = Array<String>(n) { "" }
    val r = Array<String>(n) { "" }

    repeat(n) { i ->
        s[i] = readLine().trim()
        r[i] = s[i].reversed()
    }

    // dp[i][0] 은 i 번째 string 까지 확인하고, 안뒤집었을 때 ci 합의 최소
    // dp[i][1] 은 i 번째 string 까지 확인하고, 뒤집었을 때 ci 합의 최소
    val dp = Array(n) { LongArray(2) { 10000000000000001 } }

    dp[0][0] = 0; dp[0][1] = c[0]

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        for(i in 1 until n){

            //안뒤집었을때, 뒤에 string 이 사전순으로 뒤에 있는 경우
            if (s[i]>=s[i-1]){
                dp[i][0] = dp[i-1][0]
            }

            //앞에꺼 뒤집었을때, 뒤에 string이 사전순으로 뒤에 있는 경우
            if (s[i]>=r[i-1]){
                dp[i][0] = dp[i][0].coerceAtMost(dp[i-1][1])
            }

            //지금꺼 뒤집었을때, 앞에꺼 안뒤집은것보다 사전순으로 뒤에 있는 경우
            if(r[i] >= s[i-1]){
                dp[i][1] = dp[i-1][0] + c[i]
            }

            //지금꺼 뒤집고, 앞에꺼 뒤집었을때 사전순으로 뒤에 있는 경우
            if(r[i]>=r[i-1]){
                dp[i][1] = dp[i][1].coerceAtMost(dp[i-1][1] + c[i])
            }

        }

        val min = dp[n-1][0].coerceAtMost(dp[n-1][1])
        if (min==10000000000000001){
            w.write("-1")
        }else{
            w.write("$min")
        }
        w.flush()
    }


}