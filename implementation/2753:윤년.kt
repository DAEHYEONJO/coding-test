package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()

    //윤년 : 4의 배수이면서 100의배수가 아닐때
    //      또는 400의 배수일 때
    if(n%4==0 && n%100!=0){
        println(1)
        return@with
    }

    if (n%400==0) println(1)
    else println(0)
}