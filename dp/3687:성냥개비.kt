package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val num = arrayOf(0L, 0L, 1L, 7L, 4L, 2L, 0L, 8L, 10L)
    val dpMin = LongArray(101) { Long.MAX_VALUE }
    num.forEachIndexed { index, l ->
        dpMin[index] = l
    }
    dpMin[6] = 6L

    for (i in 9 until 101) {
        for (j in 2 until 8) {
            dpMin[i] = dpMin[i].coerceAtMost(dpMin[i - j] * 10 + num[j])
        }
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        repeat(n) {
            var nn = readLine().trim().toInt()
            w.write("${dpMin[nn]} ")
            val maxNum = StringBuilder()
            maxNum.append(if (nn%2 != 0) "7" else "1")
            while(nn>3){
                maxNum.append("1")
                nn -= if (nn%2!=0) {
                    3
                }else{
                    2
                }
            }
            w.write("${maxNum.toString()}\n")
        }
        w.flush()
    }

}