package backtracking

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val a = Array(n) { IntArray(m ) }
    var notNemo = 0
    val dirs = arrayOf(intArrayOf(0,1), intArrayOf(1,0), intArrayOf(1,1))//우, 하, 대각 아래

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->

        fun dfs(depth: Int, y: Int, x: Int) {
            if (depth == n * m) {
                //종료조건 -> (n-1, m-1) 도착시 return
                for (i in 0 until n-1){
                    xLoop@for(j in 0 until m-1){
                        if (a[i][j]==1){
                            for ((dy, dx) in dirs){
                                val newY = i+dy
                                val newX = j+dx
                                if (a[newY][newX]!=1) continue@xLoop
                            }
                            notNemo++
                            return
                        }

                    }
                }
                return
            }

            val newY = if (x==m-1) (y+1)%n else y
            val newX = (x+1)%m
            // (0,0) ->...-> (0,m-1) ->...-> (1, ) ->...-> (n-1,m-1)

            dfs(depth+1, newY, newX) // 그냥 다음칸 보기
            a[newY][newX] = 1 // 1로 바꿔놓기
            dfs(depth+1, newY, newX) // 1로 바꿔놓고 다음칸 보기
            a[newY][newX] = 0 // 다시 return 되면 원래대로 돌려놓기
        }

        dfs(0, 0, -1)
        val total = 1 shl (n*m)
        w.write("${total - notNemo}\n")
        w.flush()
    }


}

