package shortest_path

import java.io.*
import java.util.*

data class P(var time: Int, val z: Int, val y: Int, val x: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    // 동, 서, 남, 북, 상, 하
    val dirs = arrayOf(intArrayOf(0,0,1), intArrayOf(0,0,-1), intArrayOf(0,1,0), intArrayOf(0,-1,0), intArrayOf(1,0,0), intArrayOf(-1,0,0))
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        outLoop@while (true) {
            val (height, row, col) = readLine().split(" ").map { it.toInt() }

            if (height == 0 && row == 0 && col == 0)
                break@outLoop

            val arr = Array(height) { Array(row) { IntArray(col) } }
            lateinit var startPos: Triple<Int, Int, Int>
            lateinit var endPos: Triple<Int, Int, Int>

            repeat(height) { h ->
                repeat(row) { r ->
                    readLine().forEachIndexed { c, value ->
                        arr[h][r][c] = when(value) {
                            'S' -> {
                                startPos = Triple(h, r, c)
                                0
                            }
                            '.' -> Int.MAX_VALUE
                            '#' -> -1
                            else -> {
                                endPos = Triple(h, r, c)
                                Int.MAX_VALUE
                            }
                        }
                    }
                }
                readLine() // 층 구별 한줄 받아주기
            }
            val pq = PriorityQueue<P>(compareBy { it.time })
            pq.add(P(0, startPos.first, startPos.second, startPos.third))

            while (pq.isNotEmpty()){
                val (time, curZ, curY, curX) = pq.poll()

                if (endPos.first == curZ && endPos.second == curY && endPos.third == curX){
                    w.write("Escaped in $time minute(s).\n")
                    continue@outLoop
                }

                if (arr[curZ][curY][curX] < time) continue

                for((dz, dy, dx) in dirs){
                    val newZ = curZ + dz
                    val newY = curY + dy
                    val newX = curX + dx
                    if (newZ in (0 until height) && newY in (0 until row) && newX in (0 until col)){
                        if (arr[newZ][newY][newX]!=-1){
                            val newTime = time + 1
                            if (newTime < arr[newZ][newY][newX]){
                                arr[newZ][newY][newX] = newTime
                                pq.add(P(newTime, newZ, newY, newX))
                            }
                        }
                    }
                }
            }
            w.write("Trapped!\n")
        }
        w.flush()
    }
}