package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    //dp[i]는 입력으로 주어진 coin 으로 i원을 만들수 있는 경우의 수다.
    //coins: 1,2,5
    //k: 10
    //1원 만들기 -> 1 -> 1
    //2원 만들기 -> 11, 2 -> 2
    //3원 만들기 -> 111, 12 -> 2
    //4원 만들기 -> 1111, 112, 22 -> 3
    //5원 만들기 -> 11111, 1112, 122, 5 -> 4개
    val (n, k) = readLine().trim().split(" ").map { it.toInt() }
    val coins = IntArray(n) { readLine().toInt() }

    val dp = IntArray(k + 1) { 0 }
    dp[0] = 1 // 초기에 주어진 coins 배열에 대한 값에 대하여
    // dp[coin] = 1 로 세팅해주기 위한 base case
    // 점화식은 dp[k] += dp[k-coin] 이다. (coin 은 coins 의 원소)
    // coin 이 1, 2, 5 이고 최종 만들고자 하는 값이 10 인 경우
    // 7원은 2원이 만들어 질 수 있는 경우에 5원을 추가한 것이고
    // 7원은 5원이 만들어 질 수 있는 경우에 2원을 추가한 것이고
    // 7원은 6원이 만들어 질 수 있느 경우에 1원을 추가한 것이기 때문에 점화식이 위와 같이 완성 된다.

    coins.forEach{ coin ->
        for(i in coin until k+1){
            dp[i]+=dp[i-coin]
        }
    }
    println(dp.last())
}