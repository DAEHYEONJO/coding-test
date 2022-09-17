package combination

import java.io.*
import kotlin.math.abs

data class Pos(val y: Int, val x: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val homePos = ArrayList<Pos>()
    val chickenPos = ArrayList<Pos>()
    repeat(n) { y ->
        readLine().split(" ").map { it.toInt() }.toIntArray().onEachIndexed { x, i ->
            when (i) {
                1 -> homePos.add(Pos(y, x))
                2 -> chickenPos.add(Pos(y, x))
            }
        }
    }
    var minAnswer = Int.MAX_VALUE

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        fun getDistance(pos1: Pos, pos2: Pos): Int {
            return abs(pos1.y - pos2.y) + abs(pos1.x - pos2.x)
        }

        fun getChickenDistance(curChickenPosList: ArrayList<Pos>): Int {
            // 집 하나당 chickenPos 와의 거리 중 min 값을 고른다
            // 모든 집을 돌면서 min 값을 전체 치킨 거리에 더해준다
            var chickenDistance = 0
            homePos.forEach { posHome ->
                var curMinDistance = Int.MAX_VALUE
                curChickenPosList.forEach { posChicken ->
                    curMinDistance = minOf(curMinDistance, getDistance(posHome, posChicken))
                }
                chickenDistance += curMinDistance
            }
            return chickenDistance
        }

        fun combination(depth: Int, r: Int, start: Int, tempResult: ArrayList<Pos>) {
            if (depth == r) {
                minAnswer = minOf(minAnswer, getChickenDistance(tempResult))
                return
            }

            for (i in start until chickenPos.size) {
                tempResult.add(chickenPos[i])
                combination(depth + 1, r, i + 1, tempResult)
                tempResult.removeLast()
            }
        }

        for (chickenCount in 1..m) {
            combination(depth = 0, r = chickenCount, start = 0, tempResult = ArrayList())
        }

        w.write("$minAnswer")
        w.flush()
    }
    close()
}