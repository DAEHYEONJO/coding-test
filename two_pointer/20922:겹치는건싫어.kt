package two_pointer

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, k) = readLine().trim().split(" ").map{ it.toInt() }
    val arr = IntArray(1) + readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    var start = 1
    var end = 1
    val counts = IntArray(100001){0}
    var ans = 0

    BufferedWriter(OutputStreamWriter(System.`out`)).use { bw ->

        while(end in (start until n+1)){
            while(end in (start until n+1) && counts[arr[end]]<k){
                counts[arr[end++]]+=1
            }
            ans = ans.coerceAtLeast(end-start)
            while (end in (start until n+1) && counts[arr[end]]>=k){
                counts[arr[start++]]-=1
            }

        }
        bw.write("$ans")
    }

}