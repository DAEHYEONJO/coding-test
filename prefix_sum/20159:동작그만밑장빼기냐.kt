package prefix_sum

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt() // n은 짝수
    val cards = readLine().split(" ").map { it.toInt() }.toIntArray()

    // 홀수 누적 합
    val oddPrefixSum = IntArray(n / 2 + 1)
    // 짝수 누적 합
    val evenPrefixSum = IntArray(n / 2 + 1)

    cards.forEachIndexed { index, card ->
        if (index % 2 == 1) { // 홀수
            oddPrefixSum[index / 2 + 1] += oddPrefixSum[index / 2] + card
        } else { // 짝수
            evenPrefixSum[index / 2 + 1] += evenPrefixSum[index / 2] + card
        }
    }
    // 1 ,3, 5, 7
    // 0  1  2  3
    var maxNum = 0
    // 밑장 내가 빼서 내가 가지기
    for (i in 0 .. n / 2) {
        maxNum = maxOf(maxNum, evenPrefixSum[i] + cards.last() + (oddPrefixSum[(n / 2) - 1] - oddPrefixSum[i]))
    }
    // 밑장 상대방이 갖기 버전
    for (i in 1 until n / 2) {
        maxNum = maxOf(maxNum, evenPrefixSum[i] + oddPrefixSum[(n / 2) - 1] - oddPrefixSum[i - 1])
    }
    println(maxNum)
}