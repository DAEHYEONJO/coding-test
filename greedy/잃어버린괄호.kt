package greedy

import java.io.*

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    // 1 + 2 + 3 - 3 + 4 + 1 - 1 - 2 - 3 + 1
    // 1 + 2 + 3 -(3 + 4 + 1)- 1 - 2 -(3 + 1)
    // (1+2+3) - (3+4+1) - 1 - 2 - (3+1)
    // (1+2+3) - (3+4+1) - (1) - (2) - (3+1)
    // '-'로 split() -> 각각 더해서 return -> return 받은 수 들 차례대로 빼주기
    val line = readLine().trim().split("-").stream().toArray()
    val firstNum = getNumberSum(line[0].toString())
    var result = 0
    line.forEach {
        result += -1*getNumberSum(it.toString())
    }
    result+=2*firstNum
    println(result)

}

fun getNumberSum(list : String) : Int = list.split("+").sumOf { it.toInt() }
