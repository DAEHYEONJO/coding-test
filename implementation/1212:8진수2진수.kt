package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().trim()
    if (n=="0") {
        println("0")
        return@with
    }
    val f = arrayOf("","1","10","11","100","101","110","111")
    val l = arrayOf("000","001","010","011","100","101","110","111")
    val ans = StringBuilder(f[n[0]-'0'])
    for(i in 1 until n.length){
        ans.append(l[n[i]-'0'])
    }
    println(ans)
}