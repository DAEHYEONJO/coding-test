package implementation

import java.io.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val likes = Array(n * n) {
        readLine().trim().split(" ").map { it.toInt() }.toIntArray()

    }
    val student = Array(n) { IntArray(n) { 0 } }
    //인접한칸: 우, 하, 좌, 상
    val dir = arrayOf(arrayOf(0, 1), arrayOf(1, 0), arrayOf(0, -1), arrayOf(-1, 0))//우 하 좌 상
    val range = (0 until n)
    val hm = HashMap<Int, IntArray>()
    likes.forEach {
        hm[it[0]] = intArrayOf(it[1], it[2], it[3], it[4])
    }

    fun getFirstList(curStudentIndex: Int): ArrayList<Pair<Int, Int>> {
        var like = 0
        val list = ArrayList<Pair<Int, Int>>()
        for (y in 0 until n) {
            for (x in 0 until n) {
                if (student[y][x] == 0) {
                    var curLikes = 0
                    for ((dy, dx) in dir) {
                        val newY = y + dy
                        val newX = x + dx
                        if (newY in range && newX in range) {
                            for (i in 1 until 5) {
                                if (likes[curStudentIndex][i] == student[newY][newX]) {
                                    curLikes++
                                    break
                                }
                            }
                        }
                    }
                    if (curLikes > like) {
                        like = curLikes
                        list.clear()
                        list.add(Pair(y, x))
                    } else if (curLikes == like) {
                        list.add(Pair(y, x))
                    }
                }
            }
        }
        return list
    }

    fun getSecondList(list: ArrayList<Pair<Int, Int>>): ArrayList<Pair<Int, Int>> {
        var maxEmptySeatCount = 0
        val ans = ArrayList<Pair<Int, Int>>()
        list.forEach { (y, x) ->
            var curEmptySeatCount = 0
            for ((dy, dx) in dir) {
                val newY = dy + y
                val newX = dx + x
                if (newY in range && newX in range) {
                    if (student[newY][newX] == 0) curEmptySeatCount++
                }
            }
            if (curEmptySeatCount > maxEmptySeatCount) {
                maxEmptySeatCount = curEmptySeatCount
                ans.clear()
                ans.add(Pair(y, x))
            } else if (curEmptySeatCount == maxEmptySeatCount) {
                ans.add(Pair(y, x))
            }
        }
        return ans
    }

    fun getSatisfaction(): Int {
        var ans = 0
        for (y in 0 until n) {
            for (x in 0 until n) {
                var cnt = 0
                val st = student[y][x]
                for ((dy, dx) in dir) {
                    val newY = y + dy
                    val newX = x + dx
                    if (newY in range && newX in range) {
                        if (student[newY][newX] in hm[st]!!) {
                            cnt++
                        }
                    }
                }

                when (cnt) {
                    1 -> ans += 1
                    2 -> ans += 10
                    3 -> ans += 100
                    4 -> ans += 1000
                }
            }
        }
        return ans
    }


    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        repeat(n * n) { studentIndex ->
            val firstList = getFirstList(studentIndex)
            if (firstList.size == 1) {
                student[firstList[0].first][firstList[0].second] = likes[studentIndex][0]
            } else {
                val secondList = getSecondList(firstList)
                secondList.sortWith(compareBy({ it.first }, { it.second }))
                student[secondList[0].first][secondList[0].second] = likes[studentIndex][0]
            }
        }

        w.write(getSatisfaction().toString())
        w.flush()
    }

}