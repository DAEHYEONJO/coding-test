package dp

import java.io.*

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val arr = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    val dp = ArrayList<Int>()
    dp.add(arr.first())
    for (i in 1 until n){
        if (dp.last() < arr[i]){
            dp.add(arr[i])
        }else{
            val index = binarySearch(dp, arr[i])
            dp[index] = arr[i]
        }
    }
    println(dp.size)
}

fun binarySearch(list: ArrayList<Int>, target: Int): Int {
    var start = 0
    var end = list.size-1
    while(start<=end){
        val mid = (start+end).div(2)
        when{
            list[mid] == target -> return mid
            list[mid] > target -> end = mid - 1
            list[mid] < target -> start = mid + 1
        }
    }
    return start
}