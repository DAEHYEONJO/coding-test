package dp

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections.max
import kotlin.math.max
//이전에 k 번째날에 상담을 잡았을 때, array[k][0]날이 지난 날 = i 번째날
//dp[i]에는 i 이전의 날 중 잡은 상담이 i-1 번째 날에 종료되는 cost의 max 값이 들어있음
//maxCost는 i 번째 날을 기준으로 이전의 상담 cost의 max값
//array[i]의 상담이 n번째 날을 넘기지 않고 끝나는지 여부 확인하고
//넘기지 않는다면 이전의 상담 cost의 max값(maxCost)를 더해서 갱신해주기
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val array = Array<IntArray>(n){readLine().split(" ").map { it.toInt()}.toIntArray()}
    val dp = Array(n+1){0}
    var maxCost = 0
    for (i in array.indices){
        val end = array[i][0] + i
        maxCost = max(dp[i], maxCost)

        if (end <= n){
            array[i][1] += maxCost
            dp[end] = max(dp[end], array[i][1])
        }
        println(array.contentDeepToString())
        println("cur: ${array[i].contentToString()}")
        println(dp.contentDeepToString())
        println()
    }
    print(dp.maxOrNull())
    this.close()
}