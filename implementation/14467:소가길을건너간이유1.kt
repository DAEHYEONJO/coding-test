package implementation

import java.io.*
fun main()  = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = IntArray(n+1){-1}
    var ans = 0
    repeat(n){
        val(nn, i) = readLine().trim().split(" ").map{ it.toInt() }
        if (a[nn]!=-1) {
            if (a[nn] != i) {
                ans++
                a[nn] = i
            }
        }else a[nn] = i
    }
    println(ans)
}


