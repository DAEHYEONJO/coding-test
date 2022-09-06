package tree

import java.io.*
import java.util.*
import kotlin.collections.HashMap

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    data class Node(var left: Int? = null, var right: Int? = null)



    val n = readLine().toInt()
    LinkedList<Int>().remove()
    val adjList = HashMap<Int, Node>()
    val set = HashSet<Int>()
    repeat(n) {
        set.add(it + 1)
    }
    repeat(n) {
        val (parent, leftChild, rightChild) = readLine().trim().split(" ").map { it.toInt() }
        val node = Node()
        if (leftChild != -1) {
            node.left = leftChild
            set.remove(leftChild)
        }
        if (rightChild != -1) {
            node.right = rightChild
            set.remove(rightChild)
        }
        adjList[parent] = node
    }

    val root = set.first()
    //println(adjList)
    val depthsMap = HashMap<Int, TreeMap<Int, Int>>()//[depth] = ([col]=node Num)
    var col = 1
    fun inorderTraversal(root: Int, depth: Int) {
        if (!adjList.containsKey(root)) {
            depthsMap.run {
                put(depth, getOrDefault(depth, TreeMap<Int, Int>()).apply { put(col++, root) })
            }
            return
        }
        val childNodes = adjList[root]
        childNodes!!.left?.let { inorderTraversal(it, depth + 1) }
        depthsMap.run {
            put(depth, getOrDefault(depth, TreeMap<Int, Int>()).apply { put(col++, root) })
        }
        childNodes.right?.let { inorderTraversal(it, depth + 1) }
    }
    inorderTraversal(root, 1)
    //println(depthsMap)

    val pq = ArrayList<Pair<Int, Int>>()
    depthsMap.forEach {
        if (it.value.size > 1) {
            val curWidth = it.value.lastEntry().key - it.value.firstEntry().key + 1
            pq.add(Pair(it.key, curWidth))
        }
    }
    if (pq.isEmpty()) {
        println("1 0")
    } else {
        pq.sortWith(compareBy({ -it.second }, { it.first }))
        println("${pq[0].first} ${pq[0].second}")
    }
}