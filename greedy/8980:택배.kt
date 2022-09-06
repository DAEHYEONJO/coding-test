package greedy

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, c) = readLine().trim().split(" ").map { it.toInt() }
    val m = readLine().toInt()
    val info = ArrayList<Triple<Int,Int,Int>>(m)
    repeat(m){
        val st = StringTokenizer(readLine())
        info.add(Triple(st.nextToken().toInt(),st.nextToken().toInt(),st.nextToken().toInt()))
    }
    info.sortWith(compareBy({it.second},{it.first}))//dst 빠른순, dst가 같다면 src 빠른순
    val capacity = IntArray(n){c}
    // src<= <dst 까지의 capacity 중에서 가장 작은 값이 현재 차에 실을 수 있는 weight 양과 같다.
    // capacity[i]는 [i]번째 마을에서 실을 수 있는 weight 의 최대양이다.
    var answer = 0
    info.forEach{
        val(curSrc, curDst, curWeight) = it
        var minCapacity = c
        for (i in curSrc-1 until curDst){
            minCapacity = minCapacity.coerceAtMost(capacity[i])
        }

        val possibleWeight = minCapacity.coerceAtMost(curWeight)
        for (i in curSrc-1 until curDst-1){
            capacity[i]-=possibleWeight
        }
        answer+=possibleWeight
    }
    println(answer)
}