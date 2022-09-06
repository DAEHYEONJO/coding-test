package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val s1 = readLine().trim()
    val s2 = readLine().trim()
    val preDp = IntArray(s2.length+1){ 0 }
    val curDp = IntArray(s2.length+1){ 0 }
    for (i in 1 until s1.length+1){
        for(j in 1 until s2.length+1){
            if (s1[i-1] == s2[j-1]){
                curDp[j] = preDp[j-1] + 1
            }else{
                curDp[j] = curDp[j-1].coerceAtLeast(preDp[j])
            }
        }
        for (k in 1 until s2.length+1){
            preDp[k] = curDp[k]
            curDp[k] = 0
        }
    }
    println(preDp.last())
}