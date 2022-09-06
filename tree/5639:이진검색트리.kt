package tree
import java.io.*
import java.nio.Buffer
import java.util.*

data class Node(val key: Int, var left: Node? = null, var right: Node? = null)

fun insertNode(root: Node?, key: Int): Node{
    var node = root
    val newNode = Node(key)
    var parent = node
    while (node!=null){
        parent = node
        node = when{
            node.key==key -> return node
            node.key < key -> node.right
            else-> node.left
        }
    }
    parent?.let {
        if (it.key>key) it.left = newNode
        else it.right = newNode
    }

    return newNode
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val root = insertNode(null, readLine().toInt())
        var num: String? = null
        while (!readLine().also { num = it }.isNullOrBlank()){
            num?.let { insertNode(root, it.toInt()) }
        }

        fun postorderTraversal(root:Node?){
            root?.let { postorderTraversal(it.left) }
            root?.let { postorderTraversal(it.right) }
            root?.let { w.write("${it.key}\n") }
        }

        postorderTraversal(root)
    }
}