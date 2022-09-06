package tree

import java.io.*
import java.util.*
import kotlin.collections.HashSet

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val t = readLine().toInt()
    repeat(t){
        val n = readLine().toInt()
        val tree = Array(n+1){LinkedList<Int>()}
        val rootSet = HashSet<Int>()
        val depthArr = IntArray(n+1)
        val parents = IntArray(n+1)
        repeat(n){
            rootSet.add(it+1)
        }
        repeat(n - 1){
            val(p, c) = readLine().split(" ").map { it.toInt() }
            rootSet.remove(c)
            tree[p].add(c)
            tree[c].add(p)
            parents[c] = p
        }
        val(u, v) = readLine().split(" ").map{it.toInt()}
        fun dfs(root: Int, depth: Int){
            if (depthArr[root]!=0) return
            depthArr[root] = depth
            tree[root].forEach { child->
                if (child!=rootSet.last())
                    dfs(child, depth+1)
            }
        }
        dfs(rootSet.last(), 0)

        fun getLCA(u: Int, v: Int){
            var a = u
            var b = v
            var depthA = depthArr[a]
            var depthB = depthArr[b]

            if (depthA>depthB) {
                b = a.also{a = b}
                depthB = depthA.also{depthA = depthB}
            }

            while(depthA<depthB){
                b = parents[b]
                depthB--
            }

            while (a!=b){
                a = parents[a]
                b = parents[b]
            }
            println(a)
        }

        getLCA(u, v)
    }

}



