package dp

import java.io.*

// dp 배열을 하나 만들었더니 메모리 초과가 떴다.
// 변수 3개만으로 최댓값을 갱신해줬다.
// pre: i-1번째까지 최댓값
// cur: i 번째에서 최댓값
// max: 전체에서 최댓값
// 연속한 수들을 더했을때 최댓값이 무엇인지 찾는 문제다.
// 초기 pre 값을 a[0]로 세팅
// a[1]에 접근해서 pre+a[1]을 한 값이 a[1]보다 크다면 전자 값으로 최댓값 갱신
// 만약에 작다면, 최댓값을 구할때 a[i]번원소부터 다시 연속한걸 더해줘야 하므로 cur = a[i]로 변경.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map{it.toInt()}.toIntArray()
    var pre = a[0]
    var cur = 0
    var max = pre
    for (i in 1 until n){
        cur = (pre+a[i]).coerceAtLeast(a[i])
        max = max.coerceAtLeast(cur)
        pre = cur
    }
    println(max)
}