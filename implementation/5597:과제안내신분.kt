package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val a = BooleanArray(30){false}
    repeat(28){
        a[readLine().toInt()-1] = true
    }
    for(i in 0 until 30){
        if (!a[i]) println(i+1)
    }
}