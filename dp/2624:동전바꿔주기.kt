package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val t = readLine().toInt()
    val k = readLine().toInt()
    val pn = Array(k){ readLine().split(" ").map{it.toInt()}.toIntArray() }.sortedBy { it[0] }
    val dp = Array(k+1) { LongArray(t + 1) { 0L } }
    dp[0][0] = 1L

    //dp[row][col]은 row 번째 동전까지 사용해서 col 원을 만들 수 있는 경우의 수다.
    //dp row size 는 k+1 이다.
    //dp[0][0] 는 아무런 동전을 사용하지 않고 0 원을 만드는 방법 수 이므로 1 이다.
    //문제의 숫자로 예를 들자면 1 원은 5개 있다.
    //아무런 동전을 사용하지 않고 0 원을 만들 수 있는 경우에수에 1원 0개를 사용하면 0원을 만들수 있다 => dp[1][0] = 1
    //아무런 동전을 사용하지 않고 0 원을 만들 수 있는 경우에수에 1원 1개를 사용하면 1원을 만들수 있다 => dp[1][1] = 1
    //아무런 동전을 사용하지 않고 0 원을 만들 수 있는 경우에수에 1원 2개를 사용하면 2원을 만들수 있다 => dp[1][2] = 1
    //아무런 동전을 사용하지 않고 0 원을 만들 수 있는 경우에수에 1원 3개를 사용하면 3원을 만들수 있다 => dp[1][3] = 1
    //아무런 동전을 사용하지 않고 0 원을 만들 수 있는 경우에수에 1원 4개를 사용하면 4원을 만들수 있다 => dp[1][4] = 1
    //아무런 동전을 사용하지 않고 0 원을 만들 수 있는 경우에수에 1원 5개를 사용하면 5원을 만들수 있다 => dp[1][5] = 1
    //1원은 5개 있으므로 최대 5원까지 만들 수 있다.
    //5원을 사용한다면 dp[1][0] 에서 5원을 0개, 1개, 2개, 3개 사용해서 만들 수 있는 경우의수 => dp[2][0], dp[2][5], dp[2][10], dp[2][15] 를 세팅해주고.
    //5원을 사용한다면 dp[1][1] 에서 5원을 0개, 1개, 2개, 3개 사용해서 만들 수 있는 경우의수 => dp[2][1], dp[2][6], dp[2][11], dp[2][16] 를 세팅해주고.
    //... 이런식으로 10원까지 모두 사용해서 dp 테이블을 채워 준다면 주어진 동전으로 t 원을 만들 수 있는 경우의수는 dp[k][t]이다.

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        for(i in 0 until k){
            for(j in 0 until t+1){
                if (dp[i][j]!=0L){
                    for (l in 0 .. pn[i][1]){
                        val possibleMoney = j + l*pn[i][0]
                        if (possibleMoney<=t){
                            dp[i+1][possibleMoney] += dp[i][j]
                        }
                    }
                }
            }
        }
        w.write("${dp.last().last()}")
        w.flush()
    }


}