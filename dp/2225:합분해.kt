package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().trim().split(" ").map { it.toInt() }
    //0 .. n 까지의 정수 k 개를 더해서 합이 n이 되는 경우의수
    //0 .. n 사이 정수 중복선택 가능
    //정수 배열 순서 바뀌면 다른것.
    //답을 1_000_000_000 으로 나눈 나머지 출력
    //    0 1 2 3 4 5 6 7 8 9   10 11 12 13 14 15 16 17 18 19 20
    //1 | 1 1 1 1 1 1 1 1 1 1   1  1  1  1  1  1  1  1  1  1  1
    //2 | 1 2 3 4 5 6 7 8 9 10  11 12 13 14 15 16 17 18 19 20 21
    //dp 배열의 row 는 숫자의 갯수다
    //dp 배열의 col 은 만드는 숫자다
    //즉, dp[k][n]의 의미는 숫자 k 개로 n 을 만들 수 있는 경우의수에 해당한다.
    //dp[1][n] 은 모두 1 이다. 왜냐하면 숫자 1개로 n을 만들 방법은 단 한개이기 때문이다.
    //dp[2][3]을 예로 들자면 2개의 숫자를 사용해서 3을 만드는 경우의 수다.
    //1개의 숫자를 사용해서 0을 만들 수 있는 0에 3을 추가해서 03 한가지가 있다
    //1개의 숫자를 사용해서 1을 만들 수 있는 1에 3을 추가해서 13 한가지가 있다
    //1개의 숫자를 사용해서 2를 만들 수 있는 2에 3을 추가해서 23 한가지가 있다
    //1개의 숫자를 사용해서 3을 만들 수 있는 3에 0을 추가해서 30 한가지가 있다
    //즉 점화식은 dp[n][k] = sigma(dp[n-1][j]) (for j in 0 .. k) 이다.
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        val dp = Array(k + 1) { IntArray(n + 1) { 0 } }
        for (i in 0 until n + 1) {
            dp[1][i] = 1
        }

        for (row in 2 until k + 1) {
            for (col in 0 until n + 1) {
                for(j in 0 .. col){
                    dp[row][col] += (dp[row-1][col-j]).mod(1000000000)
                    dp[row][col] %= 1000000000
                }
            }
        }

        w.write("${dp.last().last().mod(1000000000)}")
        w.flush()

    }


}