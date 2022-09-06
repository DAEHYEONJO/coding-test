package tree

import java.io.*
import java.util.*

data class Node2(var leftNode: String? = null, var rightNode: String? = null)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val adjList = HashMap<String, Node2>(n) // 1글자*2byte*n개 (버킷 메모리), Node class -> 6byte

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        repeat(n) {
            val (nodeNum, leftNum, rightNum) = readLine().trim().split(" ")
            val node = Node2().apply {
                if (leftNum != ".") leftNode = leftNum
                if (rightNum != ".") rightNode = rightNum
            }
            adjList[nodeNum] = node
        }

        //12byte * 26 * 3 -> 936Byte
        fun preOrderTraversal(node: String) { // 전순위탐방
            w.write(node)
            adjList[node]!!.leftNode?.let { preOrderTraversal(it) }
            adjList[node]!!.rightNode?.let { preOrderTraversal(it) }
            return
        }

        fun inOrderTraversal(node: String) { // 중순위탐방
            adjList[node]!!.leftNode?.let { inOrderTraversal(it) }
            w.write(node)
            adjList[node]!!.rightNode?.let { inOrderTraversal(it) }
            return
        }

        fun postOrderTraversal(node: String) { // 후순위탐방
            adjList[node]!!.leftNode?.let { postOrderTraversal(it) }
            adjList[node]!!.rightNode?.let { postOrderTraversal(it) }
            w.write(node)
            return
        }


        preOrderTraversal("A")
        w.write("\n")
        inOrderTraversal("A")
        w.write("\n")
        postOrderTraversal("A")
        w.flush()
    }


}