package two_pointer

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // n: 초밥 접시 수 2 ≤ N ≤ 3,000,000
    // d: 초밥의 가짓수 2 ≤ d ≤ 3,000
    // k: 연속해서 먹는 접시 수 2 ≤ k ≤ 3,000 (k ≤ N)
    // c: 쿠폰 번호 1 ≤ c ≤ d
    val (n, d, k, c) = readLine().trim().split(" ").map { it.toInt() }
    val rail = IntArray(n){readLine().toInt()}

    val deque = ArrayDeque<Int>()
    val count = HashMap<Int, Int>()
    repeat(k) {
        count.run { put(rail[it], getOrDefault(rail[it], 0) + 1) }
        deque.add(rail[it])
    }
    var ans = count.keys.size
    if (!count.containsKey(c)) ans += 1


    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        for(i in 0 until n){
            deque.run {
                val first = pollFirst()
                val firstCount = count[first]
                if (firstCount==1){
                    count.remove(first)
                }else{
                    count[first] = firstCount!! - 1
                }
                addLast(rail[(i+k)%n])
                count.run { put(rail[(i+k)%n], getOrDefault(rail[(i+k)%n], 0) + 1) }
            }
            ans = if (!count.containsKey(c)){
                ans.coerceAtLeast(count.size+1)
            }else{
                ans.coerceAtLeast(count.size)
            }
        }

        w.write("$ans\n")
        w.flush()
    }


}