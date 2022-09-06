package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    // 3개 묶음으로 사게 되면 셋 중 비싼거 2개만 가격 지불
    // 3개 묶음으로 사지 않게 되면 제품 가격 모두 지불
    // 유제품 구입의 최소 비용을 구할것
    // 전체 비용이 최소가 되려면 최대한 비싼것을 돈 안내는 쪽으로 가야함
    // 묶음으로 묶는 것을 싼거부터 묶어주기
    var n = readLine().toInt()
    val products = IntArray(n){readLine().toInt()}.sortedArray()
    var answer = 0

    while (n>=3){
        answer+=products[n-1]
        answer+=products[n-2]
        n-=3
    }
    for (i in 0 until n){
        answer+=products[i]
    }

    println(answer)


}