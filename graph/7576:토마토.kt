package graph

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (m, n) = readLine().trim().split(" ").map { it.toInt() }//col, row
    val a = Array(n) { IntArray(m) }
    val q = ArrayDeque<Triple<Int, Int, Int>>()
    val dirs = arrayOf(arrayOf(0,1),arrayOf(1,0),arrayOf(0,-1),arrayOf(-1,0))//우, 하, 좌, 상
    var zeroCount = 0
    var days = 0

    repeat(n) { y ->
        val st = StringTokenizer(readLine())
        repeat(m) { x ->
            val num = st.nextToken().toInt()
            when (num) {
                0 -> {
                    zeroCount++
                }
                1 -> {
                    a[y][x] = num
                    q.add(Triple(0, y, x))
                }
                -1 -> {
                    a[y][x] = num
                }
            }
        }
    }

    fun isValid(y: Int, x: Int): Boolean = (y in 0 until n) && (x in 0 until m) && (a[y][x] == 0)

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        while (q.isNotEmpty()) {
            val(time, curY, curX) = q.pollFirst()
            days = time
            for((dy, dx) in dirs){
                val newY = curY + dy
                val newX = curX + dx
                if (isValid(newY, newX)){
                    a[newY][newX] = 1
                    zeroCount--
                    q.add(Triple(time+1, newY, newX))
                }
            }
        }

        if(zeroCount == 0) w.write("$days")
        else w.write("-1")
        w.flush()
    }

}