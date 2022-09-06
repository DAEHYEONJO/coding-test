package two_pointer

import java.io.*
import kotlin.math.*


fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()
    var ans = Int.MAX_VALUE
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        out@for( s in 0 until n-3){
            for(e in s+3 until n){
                val n1 = a[s]+a[e]
                var m1 = s+1
                var m2 = e-1
                while(m1<m2){
                    val n2 = a[m1]+a[m2]
                    if (abs(n1-n2)==0){
                        ans = 0
                        break@out
                    }
                    if (n2<n1){
                        m1++
                    }else{
                        m2--
                    }
                    ans = ans.coerceAtMost(abs(n1-n2))
                }
            }
        }
        w.write("$ans\n")
        w.flush()
    }
}