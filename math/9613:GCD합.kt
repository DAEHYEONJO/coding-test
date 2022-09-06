package math

import java.io.*
import java.util.*

fun main()=with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val t = readLine().toInt()
        repeat(t){
            val st = StringTokenizer(readLine())
            val n = st.nextToken().toInt()
            val list = IntArray(n){0}
            repeat(n){
                list[it] = st.nextToken().toInt()
            }
            var sum = 0L
            for (i in 0 until list.size-1){
                for(j in i+1 until list.size){
                    sum+= calcGCD(list[i], list[j]).toLong()
                }
            }
            w.write("$sum\n")
        }
        w.flush()
    }
    this.close()
}

tailrec fun calcGCD(a: Int, b: Int): Int{
    val remainder = a.mod(b)
    if (remainder == 0) return b
    return calcGCD(b, remainder)
}