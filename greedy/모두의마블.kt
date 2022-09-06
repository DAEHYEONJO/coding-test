package greedy

import java.io.*

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    //두 카드 합성 -> 큰 카드의 레벨로 합성됨 & 두 카드 레벨 합 = 골드 -> 큰 레벨의 카드를 계속 합성해주자
    //젤 큰 레벨 카드는 n-1회 합성시 모두 포함됨
    //각 단계때 등장하는 젤 큰 레벨 카드를 제외한 카드들은 2번째 큰 카드부터 젤 작은 레벨의 카드까지가 된다.

    val n = readLine().toInt()
    val list = readLine().split(" ").map { it.toInt() }.toIntArray().toList()
    var maxValue = list.maxOrNull()
    var sum = list.fold(0){total, value -> total+value}-maxValue!!
    println((n-1)*maxValue + sum)


}