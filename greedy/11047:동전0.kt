package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var (n, k) = readLine().split(" ").map{it.toInt()}
    val coins = IntArray(n){readLine().toInt()}
    var answer = 0
    var index = n-1
    while (k!=0){
        if (k<coins[index]){
            index-=1
        }else{
            answer+=k/coins[index]
            k%=coins[index]
        }
    }
    println(answer)
}