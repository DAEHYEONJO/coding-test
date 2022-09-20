package combination

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // n: 문제수
    // i번째 문제 난이도는 Ai
    // 문제는 2개 이상
    // l <= 문제난이도합 <= r
    // max 난이도 - min 난이도 >= x
    val (N, L, R, X) = readLine().split(" ").map { it.toLong() }
    val problems = readLine().split(" ").map { it.toLong() }.toLongArray()

    // 문제수 2개 .. n개 바뀌가면서 combination 수행하기
    // combination 의 parameter 로 sum, min, max값 갱신해주기
    var answer = 0

    fun combination(depth: Int, r: Int, tempList: ArrayList<Long>, start: Int) {
        if (depth == r) {
            val sum = tempList.sum()
            val min = tempList.minOrNull()!!
            val max = tempList.maxOrNull()!!
            if ((sum in (L..R)) && (max - min >= X)) {
                answer++
            }
            return
        }

        for (i in start until N) {
            val curProblem = problems[i.toInt()]
            tempList.add(curProblem)
            combination(
                depth = depth + 1,
                r = r,
                tempList = tempList,
                start = i.toInt() + 1
            )
            tempList.removeLast()
        }
    }

    for (i in 2 .. N){
        combination(0, i.toInt(), ArrayList(), 0)
    }

    println(answer)
}