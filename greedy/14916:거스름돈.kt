package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val n = readLine().toInt()
        val coins = IntArray(n+1){Int.MAX_VALUE} // coins[i] => 2원과 5원을 최소로 사용하여 i 원 만들기 => 사용된 2원과 5원의 개수합
        //2 원과 5 원으로 n 원 만들기
        coins[0] = 0

        // 2원과 5원으로 i-2원, i-5원 을 만들수 있다면(무한대가 아니라면) 더 작은 값 중 +1 한 값을 i 번째 방에 갱신해주기
        for (i in 1 until n+1){
            var curMin = Int.MAX_VALUE
            curMin = if (i-5>=0 && coins[i-5]!=Int.MAX_VALUE){
                curMin.coerceAtMost(coins[i-5])
            } else if (i-2>=0 && coins[i-2]!=Int.MAX_VALUE){
                curMin.coerceAtMost(coins[i-2])
            }else{
                continue
            }
            coins[i] = curMin + 1
        }
        w.write("${coins.last().let {
            if (it!= Int.MAX_VALUE) it
            else -1
        }}")

    }
}