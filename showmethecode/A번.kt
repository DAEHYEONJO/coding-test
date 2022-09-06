package showmethecode

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val c = IntArray(1){0}+readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    val d = Array(n+1){LinkedList<Pair<Int,Int>>()}
    repeat(n){ i ->
        val p = readLine().toInt()
        repeat(p){
            val (a, dd) = readLine().trim().split(" ").map{ it.toInt() }
            d[i+1].add(Pair(a,dd))
        }
    }
    var ans = Int.MAX_VALUE
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w->
        val bucket = IntArray(n+1){0}
        val check = BooleanArray(n+1){false}
        fun dfs(depth:Int,start: Int, sum: Int){
            if (depth == n+1) {
                val cc = c.clone()
                var s = 0
                for(i in 1 until n+1){
                    val order = bucket[i]
                    s += cc[order]
                    cc[order] = 0
                    d[order].forEach {
                        if (cc[it.first]!=0){
                            cc[it.first]-=it.second
                            if (cc[it.first]<=0) cc[it.first] = 1
                        }
                    }
                }

                ans = ans.coerceAtMost(s)
                return
            }

            for(i in 1 until n+1){
                if (!check[i]){
                    bucket[depth] = i
                    check[i] = true
                    dfs(depth+1, start+1, sum)
                    check[i] = false
                }
            }
            return
        }
        dfs(1, 1, 0)
        w.write("$ans")
    }
}