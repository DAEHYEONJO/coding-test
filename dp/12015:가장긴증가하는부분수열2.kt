package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    val dp = ArrayList<Int>(n)

    fun bs(end: Int, target: Int): Int{
        var s = 0
        var e = end
        while(s<=e){
            val mid = (s+e)/2
            when{
                dp[mid] == target -> return mid
                dp[mid] < target -> s = mid + 1
                dp[mid] > target -> e = mid - 1
            }
        }
        return s
    }
    dp.add(a[0])

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        for(i in 1 until n){
            if (a[i]>dp.last()){
                dp.add(a[i])
            }else{
                val index = bs(dp.size-1, a[i])
                dp[index] = a[i]
            }
        }
        w.write("${dp.size}")
        w.flush()
    }
}