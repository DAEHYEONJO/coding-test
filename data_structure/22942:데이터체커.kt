package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val posX = Array<Pair<Int, Int>>(n) { Pair(0, 0) }
    //first: 중심 - r, second: unique num, third: 0
    //first: 중심 + r, second: unique num, third: 1
    repeat(n) {
        val (x, r) = readLine().split(" ").map { it.toInt() }
        posX[it] = Pair(x - r, x + r)
    }
    posX.sortBy { it.first }

    repeat(n - 1) {
        if (posX[it].second >= posX[it + 1].first && posX[it].second <= posX[it + 1].second) {
            println("NO")
            return@main
        }
    }
    println("YES")
}