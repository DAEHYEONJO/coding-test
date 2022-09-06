package math

import java.io.*
import java.lang.StringBuilder
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().trim()

    var newNum = n
    var count = 0

    while(true){
        count++
        if (newNum.length == 1) newNum = "0$newNum"
        val plusNum = (newNum.first().digitToInt() + newNum.last().digitToInt()).toString()

        newNum = "${newNum.last()}${plusNum.last()}"
        if (newNum.toInt() == n.toInt()) {
            println(count)
            break
        }
    }
}