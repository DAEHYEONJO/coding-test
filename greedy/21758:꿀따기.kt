package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val honey = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    val sum = honey.clone()
    var result = 0
    // 누적합 init
    for (i in 1 until n){
        sum[i] += sum[i-1]
    }

    for (i in 1 until n-1){
        // 시작, 시작, 벌통
        result = result.coerceAtLeast(sum[n-1]-honey[0]-honey[i]+sum[n-1]-sum[i-1]-honey[i])
        // 시작, 벌통, 시작
        result = result.coerceAtLeast(sum[i]-honey[0]+sum[n-2]-sum[i-1])
        // 벌통, 시작, 시작
        result = result.coerceAtLeast(sum[i-1]+sum[n-2]-honey[i])
    }

    println(result)

}