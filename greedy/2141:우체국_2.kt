package greedy

import java.io.*
import java.math.BigInteger
import java.util.*

data class XA(val x: BigInteger, val a: BigInteger) //x는 마을위치, a는 사람수

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    var curWeight = BigInteger("0")
    var leftPerson = BigInteger("0")
    var rightPerson = BigInteger("0")
    val xa = Array(n) {
        val st = StringTokenizer(readLine())
        val x = BigInteger(st.nextToken())
        val a = BigInteger(st.nextToken())
        XA(x, a)
    }
    xa.sortBy { it.x } // 마을위치 빠른순으로 sorting

    for (i in 0 until n) {
        curWeight += (xa[i].x - xa[0].x) * xa[i].a
        rightPerson += xa[i].a
    }
    //curWeight가 10^18*10^5 = 10^23이 될수 있어서, 2^63-1 보다 클수도 있음
    //Long으로하면 overflow발생해서 안됨.
    var minWeight = curWeight
    var ansX = xa[0].x
    for (i in 1 until n) {
        val diffX = xa[i].x - xa[i - 1].x
        leftPerson += xa[i - 1].a
        rightPerson -= xa[i - 1].a
        curWeight += (leftPerson - rightPerson) * diffX
        if (minWeight > curWeight) {
            minWeight = curWeight
            ansX = xa[i].x
        }
    }
    println(ansX)

}