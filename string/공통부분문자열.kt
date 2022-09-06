package string

import java.io.*


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val str1 = readLine().trim()
    val str2 = readLine().trim()

    val arr = Array(str1.length + 1) { IntArray(str2.length+1) }

    var lcsLength = 0
    for (i in 1 until arr.size){
        for (j in 1 until arr[0].size){
            if (str1[i-1]==str2[j-1]){
                arr[i][j] = arr[i-1][j-1]+1
                lcsLength = lcsLength.coerceAtLeast(arr[i][j])
            }
        }
    }
    println(lcsLength)
}