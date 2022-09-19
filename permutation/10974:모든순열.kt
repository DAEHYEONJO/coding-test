package permutation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        fun dfs(depth: Int, tempArray: ArrayList<Int>, visited: BooleanArray){
            if (depth == n){
                w.write("${tempArray.joinToString(separator = " ")}\n")
                return
            }
            for (i in 1 .. n){
                if (!visited[i]){
                    visited[i] = true
                    tempArray.add(i)
                    dfs(depth+1, tempArray, visited)
                    tempArray.removeLast()
                    visited[i] = false
                }
            }
        }

        dfs(0, ArrayList(), BooleanArray(n+1))
    }
}