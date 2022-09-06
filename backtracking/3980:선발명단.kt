package backtracking

import java.io.*

var maxAns = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){

    val t = readLine().trim().toInt()
    repeat(t){
        maxAns = 0
        val a = Array(11){readLine().trim().split(" ").map{it.toInt()}.toIntArray()}
        val visited = BooleanArray(11)
        getMax(a,visited,0,0)
        println(maxAns)
    }
}

fun getMax(a: Array<IntArray>, visited: BooleanArray, depth: Int, sum: Int){
    if (depth == 11){
        maxAns = maxOf(maxAns, sum)
        return
    }

    for (i in 0 until 11){
        if (visited[i]) continue
        if (a[depth][i]!=0){
            visited[i] = true
            getMax(a,visited,depth+1,sum+a[depth][i])
            visited[i] = false
        }
    }
}