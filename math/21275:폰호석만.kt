package math

import java.io.*
import java.lang.IllegalArgumentException

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (a, b) = readLine().split(" ")
    val resultList = ArrayList<Triple<Long, Int, Int>>()
    out@for (i in getMaxInt(a) .. 36){
        for (j in getMaxInt(b) .. 36){
            if (i!=j){
                try{
                    val xa = a.toLong(i)
                    val xb = b.toLong(j)
                    if (xa == xb){
                        resultList.add(Triple(xa, i, j))
                        if (resultList.size==2) break@out
                    }

                }catch (e : NumberFormatException){}
                catch (e2: IllegalArgumentException){}
            }
        }
    }
    when{
        resultList.size == 1->println("${resultList[0].first} ${resultList[0].second} ${resultList[0].third}")
        resultList.size == 0->println("Impossible")
        resultList.size > 1 ->println("Multiple")
    }

    // a, b 가 될 수 있는 최대 진법 check 하기
    // 최대 진법 이상의 모든 진법에 대하여 a 와 b 를 10 진수로 변환하기
    // 최대 진법이란 a 의 숫자중 가장 큰 값에 따라 결정) ep -> p = 25 이므로 ep 는 25진법 이상의 진법으로 변환 가능.
    // jh -> j = 19 이므로 19진법 이상의 진법으로 변환 가능.
}

fun getMaxInt(str: String): Int {
    return str.maxOrNull()!!.let {
        if (it.isDigit()) it.digitToInt()+1

        else it-'a'+10+1
    }
}