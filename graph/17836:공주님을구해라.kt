package graph

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m, t) = readLine().trim().split(" ").map { it.toInt() }
    val a = Array(n) { IntArray(m) }
    //0 -> 길
    //-1 -> 벽
    //-2 -> 칼
    for (i in 0 until n) {
        val st = StringTokenizer(readLine())
        for (j in 0 until m) {
            st.nextToken().toInt().let {
                when (it) {
                    2 -> a[i][j] = -2
                    1 -> a[i][j] = -1
                    else -> a[i][j] = 0
                }
            }
        }
    }

    val dirs = arrayOf(arrayOf(0, 1), arrayOf(1, 0), arrayOf(0, -1), arrayOf(-1, 0)) // 우, 하, 좌, 상
    var approachFlag = false
    var minTime = Int.MAX_VALUE
    val q = ArrayDeque<Pair<Int, Int>>() // y, x
    q.add(Pair(0, 0))

    fun isValid(y: Int, x: Int): Boolean = (y in 0 until n) && (x in 0 until m)

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        while (q.isNotEmpty()) {
            val (y, x) = q.pollFirst()

            if (a[y][x] > t) break
            if (y == n - 1 && x == m - 1) {
                approachFlag = true
                minTime = minOf(minTime, a[y][x])
            }

            for ((dy, dx) in dirs) {
                val newY = y + dy
                val newX = x + dx
                if (isValid(newY, newX)) {
                    when (a[newY][newX]) {
                        -2 -> {
                            approachFlag = true
                            a[newY][newX] = a[y][x] + 1
                            minTime = minOf(minTime, a[newY][newX] + (n - 1 - newY) + (m - 1 - newX))
                            q.add(Pair(newY, newX))
                        }
                        0 -> {
                            a[newY][newX] = a[y][x] + 1
                            q.add(Pair(newY, newX))
                        }
                    }
                }
            }
        }


        if (!approachFlag || (approachFlag && minTime > t)) {
            w.write("Fail")
        } else {
            w.write("$minTime")
        }
        w.flush()
    }

}

