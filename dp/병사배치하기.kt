package dp

import java.io.BufferedReader
import java.io.InputStreamReader

fun binarySearch(value: Int, temp : ArrayList<Int>):Int{
    var start = 0
    var end = temp.size-1
    while(start<=end){
        var mid = (start+end).div(2)
        if (temp[mid] == value) return mid
        else if (temp[mid] > value) start = mid + 1
        else end = mid - 1
    }
    return start
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }.toTypedArray()
    val temp = ArrayList<Int>()
    temp.add(arr[0])
    for (i in 1 until n){
        if (arr[i]<temp.last()) temp.add(arr[i])
        else {
            val index = binarySearch(arr[i], temp)
            temp[index] = arr[i]
        }
    }
    println(n - temp.size)
}

