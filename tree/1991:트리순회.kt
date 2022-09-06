package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val n = readLine().toInt()
        val tree = HashMap<String, Triple<String, String, String>>()
        repeat(n){
            val(node, leftChild, rightChild) = readLine().split(" ")
            tree[node] = Triple(node, leftChild, rightChild)
        }

        fun preorderTraversal(root: Triple<String, String, String>){
            w.write(root.first)
            tree[root.second]?.let { preorderTraversal(it) }
            tree[root.third]?.let { preorderTraversal(it) }
        }

        fun inorderTraversal(root: Triple<String, String, String>){
            tree[root.second]?.let { inorderTraversal(it) }
            w.write(root.first)
            tree[root.third]?.let { inorderTraversal(it) }
        }

        fun postorderTraversal(root: Triple<String, String, String>){
            tree[root.second]?.let { postorderTraversal(it) }
            tree[root.third]?.let { postorderTraversal(it) }
            w.write(root.first)
        }
        preorderTraversal(tree["A"]!!)
        w.write("\n")
        inorderTraversal(tree["A"]!!)
        w.write("\n")
        postorderTraversal(tree["A"]!!)
        w.flush()
    }
}