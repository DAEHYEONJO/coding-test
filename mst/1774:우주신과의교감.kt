package mst

import java.io.*
import java.util.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val posList = Array(1) {
        Pair(0.0, 0.0)
    } + Array(n) {
        val (x, y) = readLine().split(" ").map { it.toDouble() }
        Pair(x, y)
    } // n + 1 개 사이즈의 리스트로 좌표 배열 세팅해줄라고 앞에 원소 1개짜리 더해줌

    val parents = IntArray(n + 1) { it } // index = node, parents[index] = index의 부모

    fun getParent(a: Int): Int {
        if (parents[a] == a) return a // 종료조건
        parents[a] = getParent(parents[a]) // 내 부모의 부모로 타고 들어가서 path 줄여주기
        return parents[a]
    }

    fun unionParents(a: Int, b: Int) {
        val pA = getParent(a)
        val pB = getParent(b)
        if (pA > pB) {
            parents[pA] = pB // 값이 더 작은것을 부모로 만들어주기
        } else {
            parents[pB] = pA
        }
    }

    fun isSameParents(a: Int, b: Int) = (getParent(a) == getParent(b))

    repeat(m) {
        val (n1, n2) = readLine().split(" ").map { it.toInt() }
        if (!isSameParents(n1, n2)) // cycle이 형성될 입력은 없을것 같지만 한번 확인해줌
            unionParents(n1, n2)
    }


    val pq = PriorityQueue<Triple<Double, Int, Int>>(compareBy { it.first })

    // 각 node들 간 weight를 알아야 mst를 만들 수 있는데, weight값들이 없어서 만들어줌
    // 1000*1000
    for (i in 1 until n) {
        val (x1, y1) = posList[i]
        for (j in i + 1 until n + 1) {
            val (x2, y2) = posList[j]
            val weight = sqrt((x2 - x1).pow(2.0) + (y2 - y1).pow(2.0))
            pq.add(Triple(weight, i, j))
        }
    }

    var answer = 0.0

    while (pq.isNotEmpty()) {
        val (weight, n1, n2) = pq.poll()
        if (!isSameParents(n1, n2)) {
            unionParents(n1, n2)
            answer += weight
        }
    }

    println(String.format("%.2f", answer))


}