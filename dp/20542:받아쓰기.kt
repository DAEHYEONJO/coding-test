package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, m) = readLine().trim().split(" ").map{ it.toInt() }
    val input = CharArray(1){' '} + readLine().trim().toCharArray()
    val ans = CharArray(1){' '} + readLine().trim().toCharArray()
    // i -> i,j,l 과 같이 인식함.
    // v -> v, w 와 매칭 된다.
    // i, v를 제외한건 정확하게 인식함.
    val dp = Array(n+1){LongArray(m+1){0L} }
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        for(i in 1 until n+1){
            dp[i][0] = i.toLong()
        }
        for(i in 1 until m+1){
            dp[0][i] = i.toLong()
        }
        for(i in 1 until n+1){
            for(j in 1 until m+1){
                if(input[i] == ans[j]){
                    dp[i][j] = dp[i-1][j-1]
                }else{
                    if ((input[i] == 'i') && (ans[j]=='j'||ans[j]=='l')){
                        dp[i][j] = dp[i-1][j-1]
                    }else if((input[i]=='v') && (ans[j] =='w')){
                        dp[i][j] = dp[i-1][j-1]
                    }else{
                        dp[i][j] = ((dp[i-1][j].coerceAtMost(dp[i][j-1])).coerceAtMost(dp[i-1][j-1])) + 1L
                    }
                }
            }

        }
        w.write("${dp.last().last()}\n")
    }

}