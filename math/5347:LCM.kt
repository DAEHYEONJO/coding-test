package math

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val t = readLine().toInt()
        repeat(t){
            val st = StringTokenizer(readLine())
            var a = st.nextToken().toLong()
            var b = st.nextToken().toLong()
            if (a<b) a = b.also { b = a }
            w.write("${a*b/gcd(a,b)}\n")
        }
        w.flush()
        this.close()
    }
}

tailrec fun gcd(a: Long, b: Long): Long{
    val remainder = a.mod(b)
    if(remainder == 0L) return b
    return gcd(b, remainder)
}