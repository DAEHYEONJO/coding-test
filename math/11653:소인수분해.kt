package math

import java.io.*
import kotlin.math.sqrt

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var n = readLine().toInt()
        for (i in 2 until sqrt(n.toDouble()).toInt()+1){
            while (n.mod(i)==0){
                n/=i
                w.write("$i\n")
            }
        }
        if (n!=1){
            w.write("$n")
        }
        w.flush()
    }
}