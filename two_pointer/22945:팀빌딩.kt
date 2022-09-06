package two_pointer

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()

    var s = 0
    var e = n-1
    var ans = 0
    while(s < e){
        ans = maxOf(ans, (e-s-1)*minOf(a[s],a[e]))
        if (a[s]<a[e]){
            //a[s]가 더 작은데 e를 줄이면 어차피 간격이 줄어들어 최댓값이 갱신되지 않는다.
            s+=1
        }else{
            //반대로
            e-=1
        }
    }
    println(ans)
}