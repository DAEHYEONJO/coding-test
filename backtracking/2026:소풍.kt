package backtracking

import java.io.*
import java.util.*

lateinit var friend: Array<BooleanArray>
lateinit var degree: IntArray
var N = 0
var K = 0
var completeFlag = false

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (k, n, f) = readLine().trim().split(" ").map { it.toInt() }
    N = n
    K = k

    degree = IntArray(n + 1)
    friend = Array(n + 1) { BooleanArray(n + 1) }
    repeat(f) {
        val st = StringTokenizer(readLine())
        val s1 = st.nextToken().toInt()
        val s2 = st.nextToken().toInt()
        friend[s1][s2] = true
        friend[s2][s1] = true
        degree[s1]++
        degree[s2]++
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        for (i in 1 until n + 1) {
            if (degree[i] < k - 1) continue
            val visited = BooleanArray(n + 1)
            dfs(depth = 0, visited = visited, src = i, ArrayList())
            if (completeFlag) return@with
        }
        println("-1")

    }
}

fun dfs(depth: Int, visited: BooleanArray, src: Int, curFriends: ArrayList<Int>) {
    if (completeFlag) return
    visited[src] = true
    curFriends.add(src)
    if (depth == K - 1) {
        curFriends.sort()
        println(curFriends.joinToString(separator = "\n"))
        completeFlag = true
        return
    }

    out@ for (i in 1 until N + 1) {
        if (visited[i]) continue
        for (j in 0 until curFriends.size) {
            if (!friend[i][curFriends[j]]) {
                continue@out
            }
        }
        dfs(depth + 1, visited, i, curFriends) // 지금까지 친구였던 애들이 모두 친구인 경우 i번째도 친구 추가
    }
}