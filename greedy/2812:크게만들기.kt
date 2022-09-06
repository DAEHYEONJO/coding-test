package greedy

import java.io.*
import kotlin.collections.ArrayDeque

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, k) = readLine().split(" ").map{ it.toInt() }
    val num = readLine().trim()
    val deque = ArrayDeque<Char>().apply { add(num.first()) }
    var removeCount = 0
    // 처음에 num의 첫번째 자리를 push
    // deque의 오른쪽 원소와 num 다음 자리를 비교하여 작거나 같은 수인 경우 push
    // deque의 오른쪽 원소와 num 다음 자리를 비교하여
    // 더 큰 수인경우 deque 오른쪽 원소가 크거나 같아질때까지 Pop(단, pop은 숫자를 지우는것을 의미하므로 K 번만 pop 해야함,)
    for (i in 1 until n){
        while (removeCount!=k && deque.isNotEmpty() && deque.last() < num[i]){
            removeCount+=1
            deque.removeLast()
        }
        deque.add(num[i])
    }

    println(deque.joinToString(separator = "", limit = n-k, truncated = ""))
}