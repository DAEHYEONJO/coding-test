package shortest_path

import java.io.*
import java.util.*
import kotlin.collections.ArrayList

data class Computer(val dependOn: Int, val time: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val t = readLine().toInt()

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        repeat(t) {
            val (n, d, c) = readLine().split(" ").map { it.toInt() } // n개 컴퓨터 (1 .. n), c -> 감염당한 컴터
            val adjList = Array(n + 1) { ArrayList<Computer>() }

            repeat(d) {
                val (a, b, s) = readLine().split(" ").map { it.toInt() } // b가 감염되면 a도 s초후 감염됨
                adjList[b].add(Computer(dependOn = a, time = s))
            }

            val timeArr = IntArray(n + 1) { Int.MAX_VALUE }
            timeArr[c] = 0

            val pq = PriorityQueue<Computer>(compareBy { it.time }).apply {
                add(Computer(dependOn = c, time = 0))
            }

            var totalTime = 0
            val computerSet = mutableSetOf<Int>().apply {
                add(c)
            } // 몇 대 감염당했는지 체크하기 위한 HashSet

            while (pq.isNotEmpty()) {
                val (curComputer, curTime) = pq.poll()

                if (timeArr[curComputer] < curTime) continue

                totalTime = maxOf(totalTime, curTime)

                for (adjComputer in adjList[curComputer]) {
                    val newTime = curTime + adjComputer.time

                    if (timeArr[adjComputer.dependOn] > newTime){
                        timeArr[adjComputer.dependOn] = newTime
                        computerSet.add(adjComputer.dependOn)
                        pq.add(adjComputer.copy(time = newTime))
                    }
                }
            }

            w.write("${computerSet.size} $totalTime\n")
        }
        w.flush()
        w.close()
    }
    close()
}