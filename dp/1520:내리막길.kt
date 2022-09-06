package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        val (n, m) = readLine().split(" ").map { it.toInt() }
        val map = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
        val dp = Array(n) { IntArray(m) { 0 } }
        val dir = arrayOf(arrayOf(0, 1), arrayOf(1, 0), arrayOf(0, -1), arrayOf(-1, 0))//우,하,좌,상

        //bfs version.
        // 높은계단 -> 낮은계단으로 나와야 하므로 pq 이용
        /*val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy{-it.third})
        pq.add(Triple(0, 0, map[0][0]))
        dp[0][0] = 1
        while (pq.isNotEmpty()) {
            val (y, x, stair) = pq.poll()
            for ((dy, dx) in dir) {
                val newY = y + dy
                val newX = x + dx
                if (newY in (0 until n) && newX in (0 until m)) {
                    if (stair > map[newY][newX]) {
                        if (dp[newY][newX] == 0) {
                            pq.add(Triple(newY, newX, map[newY][newX]))
                        }
                        dp[newY][newX] += dp[y][x]
                    }
                }
            }
        }
         w.write("${dp.last().last()}")*/

        //dfs version
        val visited = Array(n) { BooleanArray(m) { false } }
        fun dfs(y: Int, x: Int): Int{
            //끝에 도달하면 방문한 좌표에서 1씩 return 받아서 더해주기 위함
            w.write("(y, x): ($y, $x) visited[y][x]: ${visited[y][x]}\n")

            if (y==n-1 && x == m-1){
                return 1
            }
            //빙문 안했던 곳이라면 상,하,좌,우 살펴서 현재 (y,x)의 계단보다 높이가 낮은 계단에 대하여
            //dfs 를 호출하고 호출한 칸으로 가는 경우의수를 현재칸에 더해준다.
            if (!visited[y][x]){
                visited[y][x] = true
                for((dy, dx) in dir){
                    val newY = y + dy
                    val newX = x + dx
                    if (newY in (0 until n) && newX in (0 until m)) {
                        if (map[y][x] > map[newY][newX]) {
                            w.write("(newY, newX): ($newY, $newX)\n")
                            dp[y][x] += dfs(newY,newX)
                            w.write("---dp---\n")
                            dp.forEach{
                                w.write("${it.contentToString()}\n")
                            }
                            w.write("---dp---\n")
                        }
                    }
                }
            }
            //이미 방문한적이 있다면 그냥 return
            return dp[y][x]
        }

        w.write("${dfs(0,0)}")
        w.flush()

    }
}