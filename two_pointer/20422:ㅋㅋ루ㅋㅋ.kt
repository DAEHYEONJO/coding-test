package two_pointer

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val str = readLine().trim()
    // s 의 부분문자열 중, 가장 긴 zzz루zzz 문자열 찾기.
    // R로만 이뤄진 문자열 ok
    // R 양쪽에 K 가 붙은 경우 ok
    val lk = ArrayList<Int>(str.length) // 왼쪽에서부터 i 번째 R 의 위치까지 몇개의 K 가 존재했는지
    val rk = ArrayList<Int>(str.length) // 오른쪽에서부터 i 번째 R 의 위치까지 몇개의 K 가 존재했는지
    var count = 0
    str.forEach { c ->
        when(c){
            'K' -> count++
            'R' -> lk.add(count)
        }
    }
    count = 0
    for (i in str.length-1 downTo 0){
        when(str[i]){
            'K'->count++
            'R'->rk.add(count)
        }
    }
    rk.reverse()
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        var s = 0
        var e = lk.size-1
        var max = 0
        while(s<=e){
            val minK = lk[s].coerceAtMost(rk[e])
            max = max.coerceAtLeast((e-s+1)+2*minK)
            if (lk[s]<rk[e]){ //왼쪽에서부터 s 까지의 K 개수가 적은 경우 s+=1 을 해줘서 minK 값을 늘려줘야 한다.
                s++
            }else{ // 반대면 e 를 줄여서 minK 값을 늘려줘야 한다.
                e--
            }
        }
        w.write("$max")
        w.flush()
    }
}