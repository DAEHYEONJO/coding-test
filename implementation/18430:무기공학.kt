package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    // n * m 배열
    val powers = Array(n) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    // y 좌표 변화하는 IntArray, x 좌표 변화하는 IntArray
    val dirs = arrayOf(
        intArrayOf(1, 0), intArrayOf(0, -1),
        intArrayOf(-1, 0), intArrayOf(0, -1),
        intArrayOf(-1, 0), intArrayOf(0, 1),
        intArrayOf(1, 0), intArrayOf(0, 1)
    ) // ㄱ, ㅢ, ㄴ, |-

    var answer = 0

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        fun validPos(y: Int, x: Int) = (y in 0 until n) && (x in 0 until m)

        fun dfs(y: Int, x: Int, sum: Int, checked: Array<BooleanArray>) {

            if (y == n) {
                answer = maxOf(answer, sum)
                return
            }

            // 0 2 4 6
            val newX = if (x == m - 1) 0 else x + 1
            val newY = if (newX == 0) y + 1 else y
            if (checked[y][x]){
                dfs(newY, newX, sum, checked)
            }else{
                for (i in 0 until 8 step 2){
                    val (dyY, dyX) = dirs[i]
                    val (dxY, dxX) = dirs[i+1]
                    val newYY = y + dyY
                    val newYX = x + dyX
                    val newXY = y + dxY
                    val newXX = x + dxX
                    if (validPos(newYY, newYX) && validPos(newXY, newXX)){
                        if (!checked[newYY][newYX] && !checked[newXY][newXX]){
                            val newChecked = Array(n){
                                checked[it].clone()
                            }
                            newChecked[y][x] = true
                            newChecked[newYY][newYX] = true
                            newChecked[newXY][newXX] = true
                            val newSum = sum + (powers[y][x]*2 + powers[newYY][newYX] + powers[newXY][newXX])
                            dfs(newY, newX, newSum, newChecked)
                        }
                    }
                }
                dfs(newY, newX, sum, checked)
            }
            return
        }

        dfs(0, 0, 0, Array(n) { BooleanArray(m) })

        w.write("$answer")
        w.flush()
    }



}