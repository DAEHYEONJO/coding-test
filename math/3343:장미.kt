package math

import java.io.*
import java.util.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var (n, a, b ,c, d) = readLine().trim().split(" ").map{it.toLong()}

    var aCount = ceil(n/a.toDouble()).toLong()
    var cCount = ceil(n/c.toDouble()).toLong()
    var aCost = aCount*b
    var cCost = cCount*d

    if (b>d){ // d 가 더 큰결로 다 swap -> c 파는 집이 비싼집
        aCost = cCost.also{cCost = aCost}
        aCount = cCount.also{cCount = aCount}
        a = c.also{c = a}
        b = d.also{d = b}
    }
    // 무조건 d 가 더 크다.
    var minMoney = cCost
    aCount = 0L
    while (cCount!=0L){
        cCount-=1
        var curCost = cCount*d + aCount*b
        var curCount = cCount*c + aCount*a
        if (curCount>=n){
            minMoney = minMoney.coerceAtMost(curCost)
        }else{
            val beforeACount = aCount
            while (curCount<n){
                curCount = cCount*c+(++aCount)*a
            }
            curCost = cCount*d + aCount*b
            if (minMoney < curCost) {
                aCount = beforeACount
            }
            minMoney = minMoney.coerceAtMost(curCost)
        }
    }
    println(minMoney.toLong())
}