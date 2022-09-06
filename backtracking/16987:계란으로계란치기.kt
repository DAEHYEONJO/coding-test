package backtracking

import java.io.*
import java.util.*

data class Egg(var s: Int, val w: Int)

lateinit var egg: Array<Egg>
var ans = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // 1번으로 2번치면 1번.s -= 2번.w
    //             2번.s -= 1번.w
    // s <= 0 -> 계란 깨짐
    // i 번째 순서에 i 번째 계란 말고 어떤 계란이던 깰 수 있음.
    val n = readLine().toInt()
    egg = Array(n) {
        val st = StringTokenizer(readLine())
        Egg(s = st.nextToken().toInt(), w = st.nextToken().toInt())
    }

    permutationExceptMe(0, IntArray(n), n)
    println(ans)
}

fun permutationExceptMe(depth: Int, result: IntArray, n: Int) {
    if (depth == n) {

        val curEgg = Array(n) {
            egg[it].copy()
        }

        var breakEgg = 0
        for (i in 0 until n) {
            val egg1 = curEgg[i]
            val egg2 = curEgg[result[i]]

            if (breakEgg == n) return
            if (egg1.s <= 0) continue
            if (egg2.s <= 0) continue

            egg1.s -= egg2.w
            egg2.s -= egg1.w

            if (egg1.s <= 0) breakEgg++
            if (egg2.s <= 0) breakEgg++

            ans = maxOf(ans, breakEgg)

        }

        return
    }

    for (i in 0 until n) {
        if (depth == i) continue // 내가 날 깰순 없음
        result[depth] = i
        if (ans == n) return
        permutationExceptMe(depth + 1, result, n)
    }
}