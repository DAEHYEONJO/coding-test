package graph

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    var st = StringTokenizer(readLine())
    var ans = m
    val trueNum = st.nextToken().toInt()
    if (trueNum == 0) {
        println(ans)
        return@with
    }

    val truePerson = IntArray(trueNum) {
        st.nextToken().toInt()
    }

    val parents = IntArray(n + 1) { i -> i }
    val adjList = Array(m) { ArrayList<Int>() }
    repeat(m) { i ->
        st = StringTokenizer(readLine())
        val t = st.nextToken().toInt()
        repeat(t) {
            adjList[i].add(st.nextToken().toInt())
        }
        adjList[i].sort() // 숫자 작은걸 parents 로 해주기 위해서 sorting 함
    }

    fun getParents(a: Int): Int {
        if (parents[a] == a) return a
        parents[a] = getParents(parents[a])
        return parents[a]
    }

    fun union(a: Int, b: Int) {
        val aParents = getParents(a)
        val bParents = getParents(b)
        if (a < b) {
            parents[bParents] = aParents
        } else {
            parents[aParents] = bParents
        }
    }

    repeat(2){
        for (i in 0 until m) {
            for (j in 0 until adjList[i].size) {
                union(adjList[i][0], adjList[i][j])
            }
        }
    }

    out@ for (i in 0 until m) {
        for (person in adjList[i]) {
            for (tPerson in truePerson){
                if (parents[tPerson] == parents[person]) {
                    ans--
                    continue@out
                }
            }
        }

    }

    println(ans)

}