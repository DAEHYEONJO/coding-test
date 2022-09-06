package greedy

import java.io.*


fun main() = with(BufferedReader(InputStreamReader(System.`in`))){

    val (n, m, k) = readLine().split(" ").map{it.toInt()}

    var result = 0
    for (i in 0 .. k){
        val minNum = ((n - i) / 2).coerceAtMost(m - (k - i))//둘중에 작은거 return
        result = result.coerceAtLeast(minNum)//둘중에 큰거 return
    }
    println(result)

}