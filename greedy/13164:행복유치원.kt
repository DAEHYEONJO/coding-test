package greedy

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, k) = readLine().trim().split(" ").map{ it.toInt() }
    val h = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    var result = 0L

    // 전체 비용 최소 -> 각 조의 max - min 이 최소 -> max 와 min의 갭차이가 작아야 한다 -> 최대한 인접한 수로 형성해야한다.
    // 1 4 7 9 10 12 / -> k = 3
    //  3 3 2 1  2
    // 1 2 3 6 10 / -> k = 3
    //  1 1 3 4
    // n = 5 면 k = 1 인 경우 4개 선택
    // k = 2 인 경우 3개 선택
    // k = 3 인 경우 2개 선택
    // k = 4 인 경우 1개 선택

    val diff = IntArray(n-1){0}
    for (i in 1 until n){
        diff[i-1] = h[i] - h[i-1]
    }
    diff.sort()
    repeat(n-k){result += diff[it]}
    println(result)


}