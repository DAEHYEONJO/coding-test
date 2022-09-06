package codeforces

import java.io.*
import java.util.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    //입력된 동전 금액의 반 초과를 내가 가져야함.
    //가지는 동전 개수는 최소화 할것.
    //크기가 큰 동전부터 갖는게 젤 best.
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        val n = readLine().trim().toInt()
        val coin = IntArray(101){0}
        val st = StringTokenizer(readLine())
        var sum = 0
        repeat(n){
            val curCoin = st.nextToken().toInt()
            sum+=curCoin
            coin[curCoin]+=1
        }
        val half = sum/2
        var ans = 0
        var curSum = 0
        out@for(i in 100 downTo 0){
            var coinCount = coin[i]
            while (coinCount>0){
                if (half<curSum) break@out
                ans+=1
                curSum+=i
                coinCount-=1
            }
        }
        w.write("$ans")
        w.flush()
    }

}