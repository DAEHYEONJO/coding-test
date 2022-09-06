package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        var k = 1
        val srcSet = HashSet<Int>()
        val dstSet = HashSet<Int>()
        val adjList = HashMap<Int, LinkedList<Int>>()
        val visited = HashMap<Int, Boolean>()

        while(true){
            val stringTokenizer = StringTokenizer(readLine())
            while (stringTokenizer.hasMoreTokens()){
                val src = stringTokenizer.nextToken().toInt()
                val dst = stringTokenizer.nextToken().toInt()
                if (src == 0 || dst == 0){ //초기화 && tree 판별 && 출력
                    if (adjList.isEmpty()) {
                        w.write("Case $k is a tree.\n")
                        k++
                        continue
                    }
                    srcSet.removeAll(dstSet)
                    if (srcSet.isEmpty() || srcSet.size != 1){
                        w.write("Case $k is not a tree.\n")
                    }
                    else{
                        val root = srcSet.first()
                        val flag = isTree(adjList, visited, root)
                        if (flag) w.write("Case $k is a tree.\n")
                        else w.write("Case $k is not a tree.\n")
                    }
                    srcSet.clear()
                    dstSet.clear()
                    adjList.clear()
                    visited.clear()
                    k++
                }else if (src < 0 && dst < 0){
                    return@main
                }else{
                    srcSet.add(src)
                    dstSet.add(dst)
                    adjList.run{
                        put(src, getOrDefault(src, LinkedList<Int>()).apply{add(dst)})
                    }
                    visited[src] = false
                    visited[dst] = false
                }
            }
        }
    }
}

fun isTree(adjList: HashMap<Int, LinkedList<Int>>, visited: HashMap<Int, Boolean>, root: Int):Boolean{
    val queue = ArrayDeque<Int>()
    queue.add(root)
    while (queue.isNotEmpty()){
        val curNode = queue.pollFirst()
        if (visited[curNode] == true) return false
        visited[curNode] = true
        adjList[curNode]?.let{
            for (node in it){
                queue.add(node)
            }
        }
    }
    return true
}