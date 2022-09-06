package greedy

import java.io.*
import java.util.*
import kotlin.math.abs

// 1 3 -> (1-1)*0
// 2 5 -> (2-1)*2
// 3 3 -> (2-1)*2 + (3-1)*3
// 5 10 -> (2-1)*2 + (3-1)*3 + (5-1)*10
// 우체국 ~ 각 사람들까지의 거리의 합이 최소가되는 곳에 우체국 세우기
// 사람들까지 거리 = (우체국 ~ 나라[i]까지 거리) * 나라[i]의 사람 수
// 1에 세움 -> 5(2-1) + 3(3-1) + 10(5-1) = 5+6+40 = 51
// 2에 세움 -> 3(2-1) + 3(3-2) + 10(5-2) = 3+3+30 = 36
// 2에 세운 것은 -> 1에 세운 것에서 -3*(2-1) - 10*(2-1) - 5*(2-1) + 3*(2-1) 함
// 이것은 우체국을 2로 옮기면서 마을 2의 사람들이 1의 마을로 가는 거리 3*(2-1) 이 추가된것을 의미하고
// 3, 5 마을 입장에서는 3마을 사람들이 1마을로 갈때 비용에서 2마을로만 가면 되니깐 3마을 3명의 사람들이 2마을에서 1마을로 갈때 비용을 제외한것 = -3*(2-1)
// 5 마을 사람들이 1마을로 갈 때의 비용에서 2마을로만 가면 되니깐 마찬가지로 5마을 10명의 사람들이 2마을에서 1마을로 갈 때 비용을 제외한것 = -10*(2-1)
// 을 빼주면 된다.
// 위의 식을 정리하면 우체국을 제일 왼쪽 마을에서 오른쪽 마을로부터 옮기면서 왼쪽 마을에 새로 생긴사람, 오른쪽 마을에서 사라진 사람수를 갱신해주고,
// i 번째 마을 ~ 0번째 마을의 거리차이 값을 갱신해주면 된다.
// 이렇게 풀면 long overflow 가 발생할 수 있다.
//
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val n = readLine().toInt()
    val xa = Array(n) { Pair(0L, 0L) }
    repeat(n) { i ->
        val st = StringTokenizer(readLine().trim())
        xa[i] = Pair(st.nextToken().toLong(), st.nextToken().toLong())//마을 위치, 사람수
    }
    xa.sortBy { it.first }
    val personSum = LongArray(n){0L}.apply { this[0] = xa[0].second }
    for (i in 1 until n){
        personSum[i] = personSum[i-1] + xa[i].second
    }
    var start = 0
    var end = n-1
    while(start<=end){
        val mid = (start+end).div(2)
        val leftSum = personSum[mid]
        val rightSum = personSum[n-1]-personSum[mid]
        if (leftSum == rightSum){
            println(xa[mid].first)
            return@with
        }
        else if(leftSum>rightSum){
            end = mid - 1
        }else{
            start = mid + 1
        }
    }
    println(xa[start].first)
}