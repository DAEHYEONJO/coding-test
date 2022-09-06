package dp

import java.io.*
import kotlin.math.ceil

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (c, n) = readLine().trim().split(" ").map { it.toInt() }
    // c명 이상을 모집했을때 최소 비용을 구하는 문제다.
    val costPerson = Array(n) { readLine().trim().split(" ").map { it.toInt() }.toIntArray() }.sortedArrayWith(compareBy { it[0] })
    // input 은 cost, person 이다. => cost 당 얻을 수 있는 person
    val maxCost = costPerson.last()[0]
    val dp = IntArray(c*maxCost+1){0}
    // dp 배열은 index: 비용, value: 사람수다
    // 즉, dp[비용] 에 해당하는 최대 모집 인원수가 몇명인지 이다.
    // dp의 비용을 0 부터 size-1 까지 반복하면서 value 값을 갱신하면 이전 비용보다 현재 비용의 사람수가 무조건 같거나 늘어나게 된다.
    // 왜냐하면 비용을 많이 투자하면 사람도 당연히 많이 모을 수 있기 때문이다.
    // 따라서 비용을 증가시켜가면서, 최초로 c 이상의 사람이 등장하는 순간 비용이 문제에서 구하고자 하는 최소 비용이다.

    for (i in dp.indices){
        costPerson.forEach {
            val (cost, person) = it
            if (i-cost>=0){
                dp[i] = dp[i].coerceAtLeast(dp[i-cost]+person)
            }
            if (dp[i]>=c){
                println(i)
                return@with
            }
        }
    }
}