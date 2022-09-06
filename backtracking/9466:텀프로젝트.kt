package backtracking

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val t = readLine().toInt()

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        repeat(t) {
            val n = readLine().toInt()
            val a = IntArray(1) + readLine().split(" ").map { it.toInt() }.toIntArray()
            val visited = IntArray(n + 1)
            val firstVisitStudent = IntArray(n + 1)

            var yesTeamMembers = 0
            for (i in 1 until n + 1) {
                if (visited[i] > 0) continue
                yesTeamMembers += dfs(
                    a = a,
                    visited = visited,
                    firstVisitStudent = firstVisitStudent,
                    src = i,
                    cur = i,
                    count = 1
                )
            }
            w.write("${n-yesTeamMembers}\n")
        }
    }

}

fun dfs(a: IntArray, visited: IntArray, firstVisitStudent: IntArray, src: Int, cur: Int, count: Int): Int {
    if (visited[cur] > 0) { // 현재 node가 이미 방문했던 적이 있었다면
        if (firstVisitStudent[cur]!=src) return 0 // 다른 for문에서 방문했떤 node이다. -> cycle 미존재
        return count-visited[cur] // cur node를 몇번째 방문했는지 (count) - 현재 node를 이전에 방문했을때 몇번째로 방문 했었었는지
    }

    visited[cur] = count
    firstVisitStudent[cur] = src
    return dfs(a, visited, firstVisitStudent, src, a[cur], count+1)
}
