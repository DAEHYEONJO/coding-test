package bruteforce

import java.io.*
import java.util.*

private lateinit var team: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0)) // 우, 하, 좌, 상
private var n = 0
private var m = 0

data class Pos(val y: Int, val x: Int)

fun getMemberCount(y: Int, x: Int, teamChar: Char): Int {
    var count = 0
    val q = ArrayDeque<Pos>().apply {
        add(Pos(y = y, x = x))
    }

    while (q.isNotEmpty()) {
        val (curY, curX) = q.pollFirst()
        count++
        visited[curY][curX] = true

        for ((dy, dx) in dirs) {
            val newY = curY + dy
            val newX = curX + dx
            if (!(newY in 0 until n && newX in 0 until m)) continue
            if (visited[newY][newX]) continue
            if (team[newY][newX] != teamChar) continue
            visited[newY][newX] = true
            q.add(Pos(y = newY, x = newX))
        }

    }

    return count*count
}

fun getAllTeamCount(teamChar: Char): Int{
    var memberCount = 0
    for (row in 0 until n) {
        for (col in 0 until m) {
            if (team[row][col] != teamChar) continue
            if (visited[row][col]) continue
            memberCount += getMemberCount(row, col, teamChar)
        }
    }
    return memberCount
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    readLine().split(" ").map { it.toInt() }.let {
        m = it[0]
        n = it[1]
    }

    team = Array(n) {
        readLine().toCharArray()
    }

    visited = Array(n) {
        BooleanArray(m)
    }

    println("${getAllTeamCount('W')} ${getAllTeamCount('B')}")

}