package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val x = readLine().toInt()
    val (m, n) = readLine().split(" ").map { it.toInt() }
    var pizzaA = IntArray(m) {
        readLine().toInt()
    }
    pizzaA += pizzaA

    var pizzaB = IntArray(n) {
        readLine().toInt()
    }
    pizzaB += pizzaB

    val prefixSumPizzaA = IntArray(m + m + 1)
    val prefixSumPizzaB = IntArray(n + n + 1)

    for (i in 1 until m + m + 1) {
        prefixSumPizzaA[i] += prefixSumPizzaA[i - 1] + pizzaA[i - 1]
    }
    for (i in 1 until n + n + 1) {
        prefixSumPizzaB[i] += prefixSumPizzaB[i - 1] + pizzaB[i - 1]
    }

    fun getPizzaSumList(prefixSum: IntArray, maxSize: Int): ArrayList<Int> {
        val pizzaSumList = ArrayList<Int>().apply { add(0) }
        for (size in 0 until maxSize - 1) {
            var s = 0
            while (s < maxSize) {
                val e = s + size
                val sum = prefixSum[e + 1] - prefixSum[s]
                pizzaSumList.add(sum)
                s++
            }
        }

        return pizzaSumList.apply {
            add(prefixSum[maxSize])
            sort()
        }
    }

    fun lowerBound(list: ArrayList<Int>, target: Int): Int {
        var s = 0
        var e = list.size
        var flag = false
        while (s < e) {
            val m = (s + e) / 2
            if (list[m] < target) {
                s = m + 1
            } else {
                if (list[m] == target) flag = true
                e = m
            }
        }
        return if (flag) s else -1
    }

    fun upperBound(list: ArrayList<Int>, target: Int): Int {
        var s = 0
        var e = list.size
        var flag = false
        while (s < e) {
            val m = (s + e) / 2
            if (list[m] <= target) {
                if (list[m] == target) flag = true
                s = m + 1
            } else {
                e = m
            }
        }
        return if (flag) s else -1
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val pizzaAList = getPizzaSumList(prefixSumPizzaA, m)
        val pizzaBList = getPizzaSumList(prefixSumPizzaB, n)

        var answer = 0
        pizzaAList.forEach { pizzaA ->
            if (pizzaA > x) return@forEach
            val target = x - pizzaA
            val count = upperBound(pizzaBList, target) - lowerBound(pizzaBList, target)
            answer += count
        }

        w.write("$answer")
        w.flush()
    }
    close()
}