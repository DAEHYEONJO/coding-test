package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        var (a, b) = readLine().split(" ").map { it.toLong() }
        var result = 0

        while (a<=b){
            when{
                a==b -> {
                    w.write("${result+1}")
                    return@with
                }
                b.mod(10)==1 -> b/=10
                b.mod(2)==0 -> b/=2
            }
            result+=1
        }

        w.write("-1")
        w.flush()
    }

}