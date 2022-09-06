package string

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val t = readLine().toInt()
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        out@for (i in 0 until t){
            val n = readLine().toInt()
            val phoneNumbers = Array(n){
                readLine().trim()
            }
            phoneNumbers.sort()
            for (j in 0 until n-1){
                if (phoneNumbers[j+1].startsWith(prefix = phoneNumbers[j])){
                    w.write("NO\n")
                    continue@out
                }
            }
            w.write("YES\n")
        }
        w.flush()
    }
}