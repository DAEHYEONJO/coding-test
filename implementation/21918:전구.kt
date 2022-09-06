package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, m) = readLine().trim().split(" ").map{ it.toInt() }
    val s = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        repeat(m){
            val(a, b, c) = readLine().trim().split(" ").map{ it.toInt() }
            when(a){
                1->{
                    s[b-1] = c
                }
                2->{
                    for(i in b-1 until c){
                        s[i] = if (s[i]==1) 0 else 1
                    }
                }
                3->{
                    for(i in b-1 until c){
                        s[i] = 0
                    }
                }
                4->{
                    for(i in b-1 until c){
                        s[i] = 1
                    }
                }
            }
        }
        w.write(s.joinToString(separator = " "))
        w.flush()
    }

}