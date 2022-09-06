package codeforces

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val w = readLine().toInt()
    BufferedWriter(OutputStreamWriter(System.`out`)).use { br ->
        if (w%2==0 && w >=4){
            br.write("YES")
        }else{
            br.write("NO")
        }
        br.flush()
    }
}