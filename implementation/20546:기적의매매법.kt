package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map { it.toInt() }.toIntArray()

    var jhMoney = n
    var jhWeek = 0
    a.forEach { jooga ->
        if (jhMoney >= jooga) {
            jhWeek += jhMoney / jooga
            jhMoney -= (jhMoney / jooga) * jooga
        }
    }
    val jhAns = jhMoney + a.last() * jhWeek

    var smMoney = n
    var smWeek = 0

    var lim = a.size-1
    for (i in 0 until lim) {
        if (i+3<lim && (a[i+1]>a[i] && a[i+1] < a[i+2])){ // 증가세 -> 팔기
            smMoney += smWeek*a[i+3]
            smWeek = 0
        }

        if (i+3<lim && (a[i+1]<a[i] && a[i+1]>a[i+2]) ){ // 하락세 -> 사기
            if (smMoney >= a[i+3]){
                smWeek += smMoney/a[i+3]
                smMoney -= (smMoney/a[i+3])*a[i+3]
            }
        }
    }
    val smAns = smMoney + a.last()*smWeek

    when{
        jhAns > smAns -> println("BNP")
        jhAns < smAns -> println("TIMING")
        else -> println("SAMESAME")
    }
}
//3원 남고 3주 매수함, + 18 = 21