package implementation

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val t = readLine().toInt()
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        repeat(t){
            val n = readLine().toInt()
            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE
            val st = StringTokenizer(readLine())
            repeat(n){
                val curNum = st.nextToken().toInt()
                if (curNum < min){
                    min = curNum
                }

                if (curNum > max){
                    max = curNum
                }
            }
            w.write("$min $max\n")
        }
        w.flush()
    }

}