package tree

import java.io.*
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.ceil
import kotlin.math.log2

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val t = readLine().toInt()
    repeat(t){
        val n = readLine().toInt()
        val tree = Array(n+1){LinkedList<Int>()}
        val rootSet = HashSet<Int>()
        val depthArr = IntArray(n+1)
        val parents = Array(n+1){IntArray(ceil(log2(n.toDouble())).toInt())}
        repeat(n){
            rootSet.add(it+1)
        }
        repeat(n - 1){
            val(p, c) = readLine().split(" ").map { it.toInt() }
            rootSet.remove(c)
            tree[p].add(c)
            tree[c].add(p)
            parents[c][0] = p//c의 2^0 번째 부모 초기화
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

        fun initParents(){
            for (j in 1 until parents[0].size){
                for (i in 1 until parents.size){
                    parents[i][j] = parents[parents[i][j-1]][j-1]
                }
            }
        }

        fun getLCA(u: Int, v: Int): Int{
            var a = u
            var b = v
            if (depthArr[a]>depthArr[b]) b = a.also{a = b} // a에 항상 depth가 작은 값이 담기게 된다.
            //현재상태 : b가 a보다 depth 가 깊으므로, b의 depth를 조정 하여 a의 depth와 맞춰준다.
            for(i in parents[0].size-1 downTo 0){
                if (depthArr[b] - depthArr[a]>=(1 shl i)){
                    b = parents[b][i]
                }
            }
            if (a==b) return a

            for (i in parents[0].size-1 downTo 0){
                if(parents[b][i]!=parents[a][i]){
                    b = parents[b][i]
                    a = parents[a][i]
                }
            }

            return parents[a][0]
        }
        dfs(rootSet.last(), 0)
        initParents()
        println(getLCA(u, v))
    }

}



