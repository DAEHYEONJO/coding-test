package prefix_sum

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val honey = readLine().split(" ").map { it.toInt() }.toIntArray()
    // 벌이 시작한 장소는 어떤 벌도 꿀을 딸 수 없다.
    val prefixSum = IntArray(n + 1)
    var answer = 0

    for (i in 1 until n + 1) {
        prefixSum[i] += prefixSum[i - 1] + honey[i - 1]
    }

    // 벌 벌 벌통 or 벌통 벌 벌 에서
    // 벌통은 가장 끝에 있어야 함 -> 왜냐면 끝에 없으면 못먹는 꿀들이 생겨서
    // 그리고 1개의 벌도 마찬가지로 벌통과 젤 반대되는 쪽에 있어야 한다. 그렇지 않으면 못먹는 꿀ㅇ들이 생김
    // 나머지 1개는 벌통과 꿀 사이어딘가에 있으면 된다, 최댓값이 되는 포지션을 예측할 수 없다.
    // 왜냐면 i번째에 위치해있을때 꿀 값이 100000000이고 나머지가 다 1이라면 해당 위치에 벌이 있으면 안됨. (반례 존재 가능)

    for (i in 2 until n) { // i -> 벌통 Position
        // 벌, 벌통, 벌
        val beeBoxBee = prefixSum[i]-prefixSum[1] + prefixSum[n-1]-prefixSum[i-1]
        // 벌 벌 벌통
        val beeBeeBox = 2*(prefixSum[n]-prefixSum[i]) + prefixSum[i-1] - prefixSum[1]
        // 벌통 벌 벌
        val boxBeeBee = 2*(prefixSum[i-1] - prefixSum[0]) + (prefixSum[n-1] - prefixSum[i])
        answer = maxOf(answer, beeBoxBee)
        answer = maxOf(answer, beeBeeBox)
        answer = maxOf(answer, boxBeeBee)
    }

    println(answer)
}