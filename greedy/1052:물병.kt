package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){

    //10000000
    var(n,k) = readLine().trim().split(" ").map{ it.toInt() }
    // n개로 k개를 넘지 않는 비어있지 않은 물병 만들기
    // => n개로 k개 이하의 채워진 물병 만들기
    // 항상 2^0L 짜리 물병을 합치므로 최대한 합쳤을때 남은 물들은 2^k 형태
    // 1 1 => 2 0 => 10_(2)
    // 1 1 1 => 2 1 => 101_(2)
    // 1 1 1 1 => 2 2 => 4 => 100_(2)
    // 1 1 1 1 1 => 2 2 1 => 4 1 => 101_(2)
    // 1 1 1 1 1 1 => 2 2 2 => 4 2 => 110_(2) => 4L, 2L 물병 남았단 얘기
    // bit 1인 수만큼 물병이 남았단 얘기다.
    var ans = 0
    while(n.countOneBits()>k){ // 숫자 n의 1bit 개수가 K개 이하가 되면 break
        ans++
        n+=1
    }
    println(ans)
}