package string

import java.io.*
import java.util.*
import kotlin.collections.HashMap

fun getStringLength(w: String, k: Int): IntArray {
    if (k==1) return intArrayOf(1, 1)

    val charMap = HashMap<Char, Int>()
    var minSize = Int.MAX_VALUE
    var maxSize = 0

    w.forEach { c ->
        charMap[c] = (charMap[c] ?: 0) + 1
    }

    charMap.filter {
        it.value >= k // k보다 커도 움직이면서 k를 만족시킬 수 있음
    }.keys.forEach { key ->
        val pos = ArrayList<Int>()
        w.forEachIndexed { index, c ->
            if (c==key) pos.add(index)
        }

        var s = 0
        var e = k-1
        while (e < pos.size){
            minSize = minOf(minSize, pos[e]-pos[s]+1)
            maxSize = maxOf(maxSize, pos[e]-pos[s]+1)
            s++
            e++
        }
    }

    return intArrayOf(minSize, maxSize)
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val t = readLine().toInt()

    BufferedWriter(OutputStreamWriter(System.`out`)).use { bw ->
        repeat(t) {
            val w = readLine().trim()
            val k = readLine().toInt()

            val (minValue, maxValue) = getStringLength(w, k)
            if (minValue == Int.MAX_VALUE || maxValue == 0) {
                bw.write("-1\n")
            } else {
                bw.write("$minValue $maxValue\n")
            }
        }
        bw.flush()
    }

}
