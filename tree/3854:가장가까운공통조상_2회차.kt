package tree

import java.io.*
import java.util.*
import kotlin.math.log2
var root = -1
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val t = readLine().toInt()

        repeat(t){
            val n = readLine().toInt()
            val adjList = Array<LinkedList<Int>>(n){LinkedList()}
            val pSet = HashSet<Int>()
            val cSet = HashSet<Int>()
            val depths = IntArray(n){0}
            val parents = Array<IntArray>(n){IntArray(kotlin.math.ceil(log2(n.toDouble())).toInt())}

            repeat(n-1){
                val (parent, child) = readLine().split(" ").map{it.toInt()}
                adjList[parent-1].add(child-1)
                adjList[child-1].add(parent-1)
                parents[child-1][0]=parent-1
                pSet.add(parent-1)
                cSet.add(child - 1)
            }
            pSet.removeAll(cSet)
            root = pSet.first()

            for(i in 1 until parents[0].size){
                for (j in 1 until parents.size){
                    parents[j][i] = parents[parents[j][i-1]][i-1]
                }
            }

            dfs(adjList, depths, 0, root)

            val(u, v) = readLine().split(" ").map{ it.toInt() }
            w.write("${getLCA( u-1, v-1, depths, parents)+1}\n")
        }
        w.flush()
        this.close()
    }
}

fun dfs(adjList: Array<LinkedList<Int>>, depths: IntArray, h: Int, node: Int){
    if (depths[node]!=0) return
    depths[node] = h
    adjList[node].forEach {
        if (it!=root) dfs(adjList, depths, h+1, it)
    }
}

fun getLCA(u: Int, v: Int, depths: IntArray, parents:Array<IntArray>): Int{
    // u, v 둘중에 depth 가 깊은것이 뭔지 모르므로, b 에다가 u, v 중 depth가 깊은 애를 넣어주자
    var a = u
    var b = v
    if (depths[a]>depths[b]) b = a.also{ a=b }
    // b와 a 의 깊이 차이가 나는 경우 b 를 a 의 depth 와 같도록 올려주자.
    // depth 차이가 5 인 경우 => 5_(10) = 101_(2) 이므로 b의 2^2 parent 의 2^0 parents 로 옮겨주면 된다.

    for (i in parents[0].size - 1 downTo 0){
        if (depths[b]-depths[a]>= (1 shl i)){
            b = parents[b][i]
        }
    }

    // depth 가 다른 a 와 b 중 depth 가 깊은 b 를 a 의 depth 와 같도록 올렸을 때, 두 node가 같다면 LCA다.
    if (a == b) return a

    // 두 node의 depth를 같도록 맞췄으므로 LCA 를 구해보자.
    for(i in parents[0].size -1 downTo 0){
        if (parents[a][i]!=parents[b][i]){
            a = parents[a][i]
            b = parents[b][i]
        }
    }
    return parents[a][0]
}