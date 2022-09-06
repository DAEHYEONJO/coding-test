package shortest_path

import java.io.*

data class Pos(var time: Int, val z: Int, val y: Int, val x: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    // 동, 서, 남, 북, 상, 하
    val dirs = arrayOf(intArrayOf(0,0,1), intArrayOf(0,0,-1), intArrayOf(0,1,0), intArrayOf(0,-1,0), intArrayOf(1,0,0), intArrayOf(-1,0,0))
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        outLoop@while (true) {
            val (height, row, col) = readLine().split(" ").map { it.toInt() }

            if (height == 0 && row == 0 && col == 0)
                break

            val arr = Array(height) { Array(row) { CharArray(col) } }
            lateinit var startPos: Triple<Int, Int, Int>
            lateinit var endPos: Triple<Int, Int, Int>

            repeat(height) { h ->
                repeat(row) { r ->
                    readLine().forEachIndexed { c, value ->
                        if (value == 'S') startPos = Triple(h, r, c)
                        else if(value == 'E') endPos = Triple(h, r, c)
                        arr[h][r][c] = value
                    }
                }
                readLine() // 층 구별 한줄 받아주기
            }

            val q = java.util.ArrayDeque<P>()
            q.add(P(0, startPos.first, startPos.second, startPos.third))
            val visited = Array(height) { Array(row) { BooleanArray(col) } }
            visited[startPos.first][startPos.second][startPos.third] = true

            while (q.isNotEmpty()){
                val(time, curZ, curY, curX) = q.pollFirst()

                if (curZ == endPos.first && curY == endPos.second && curX == endPos.third){
                    w.write("Escaped in $time minute(s).\n")
                    continue@outLoop
                }

                for ((dz, dy, dx) in dirs){
                    val newZ = curZ + dz
                    val newY = curY + dy
                    val newX = curX + dx

                    if (newZ in (0 until height) && newY in (0 until row) && newX in (0 until col)){
                        if (visited[newZ][newY][newX]) continue
                        val next = arr[newZ][newY][newX]
                        if (next == '.' || next == 'E'){
                            visited[newZ][newY][newX] = true
                            q.add(P(time+1, newZ, newY, newX))
                        }
                    }
                }
            }


            w.write("Trapped!\n")
        }
        w.flush()
    }
}