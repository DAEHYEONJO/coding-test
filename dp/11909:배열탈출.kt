package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt() // 1<=n<=2222
    val a = Array(n) { readLine().trim().split(" ").map { it.toInt() }.toIntArray() }
    val dir = arrayOf(arrayOf(0, 1), arrayOf(1, 0)) // 오른쪽, 아래
    val range = (0 until n)
    val dp = Array(n) { IntArray(n) { Int.MAX_VALUE } }
    dp[0][0] = 0



    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        fun setValue(y: Int, x: Int, newY: Int, newX: Int, weight: Int) {
            var newWeight = weight
            if (newX in range && newY in range) {
                if (a[y][x] <= a[newY][newX]) {
                    newWeight += (a[newY][newX] - a[y][x] + 1)
                }
                dp[newY][newX] = dp[newY][newX].coerceAtMost(newWeight)
            }
        }

        for (y in 0 until n) {
            for (x in 0 until n) {
                var newWeight = dp[y][x]
                when {
                    y == n - 1 -> setValue(y, x, y, x + 1, newWeight)

                    x == n - 1 -> setValue(y, x, y + 1, x, newWeight)

                    else -> {
                        for ((dy, dx) in dir) {
                            val newY = y + dy
                            val newX = x + dx
                            newWeight = dp[y][x]
                            setValue(y, x, newY, newX, newWeight)
                        }
                    }
                }
            }

        }
        w.write("${dp[n - 1][n - 1]}")
        w.flush()
    }

}