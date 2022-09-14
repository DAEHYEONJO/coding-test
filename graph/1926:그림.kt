package graph

import java.io.*
import java.util.*

fun String.toBoolean() = (this == "1")

data class PicturePos(val y: Int, val x: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val picture = Array(n) {
        readLine().split(" ").map { it.toBoolean() }.toBooleanArray()
    }
    val visited = Array(n) {
        BooleanArray(m)
    }
    val dirs = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))

    fun isValidPos(y: Int, x: Int) = (y in 0 until n) && (x in 0 until m)

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        fun bfs(y: Int, x: Int): Int {
            val q = ArrayDeque<PicturePos>().apply {
                add(PicturePos(y = y, x = x))
            }

            var size = 0

            visited[y][x] = true

            while (q.isNotEmpty()) {
                val (curY, curX) = q.pollFirst()

                size++

                for ((dy, dx) in dirs) {
                    val newY = curY + dy
                    val newX = curX + dx
                    if (isValidPos(newY, newX) && !visited[newY][newX] && picture[newY][newX]) {
                        visited[newY][newX] = true
                        q.add(PicturePos(y = newY, x = newX))
                    }
                }
            }

            return size
        }

        var pictureCount = 0
        var maxSize = 0

        for (row in 0 until n) {
            for (col in 0 until m) {
                if (visited[row][col]) continue
                if (!picture[row][col]) continue

                pictureCount++
                val curSize = bfs(row, col)
                maxSize = maxOf(maxSize, curSize)
            }
        }

        w.write("${pictureCount}\n")
        w.write("${maxSize}\n")
        w.flush()
    }


}