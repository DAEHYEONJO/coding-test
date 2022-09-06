package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    data class Node(val key: Int, var left: Node? = null, var right: Node? = null){

    }
    val n = readLine().toInt()
    val inorder = IntArray(n){0}
    val inorderMap = HashMap<Int, Int>()
    val st = StringTokenizer(readLine())
    repeat(n){
        inorder[it] = st.nextToken().toInt()
        inorderMap[inorder[it]] = it
    }
    val postorder = readLine().trim().split(" ").map{ it.toInt() }
    val root = postorder.last()

    var i = n-1
    fun getTree(start: Int, mid: Int, end: Int): Node?{
        if (start>end || i == 0){
            ++i
            if (start<=end) return Node(inorder[mid])
            return null
        }
        val root = Node(postorder[i])
        root.right = getTree(mid + 1, inorderMap[postorder[--i]]!!,end)
        root.left = getTree(start, inorderMap[postorder[--i]]!!,mid - 1)
        return root
    }
    val tree = getTree(0, inorderMap[root]!!, n-1)
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->

        fun preorderTraversal(root: Node?){
            root?.let {
                w.write("${it.key} ")
                preorderTraversal(it.left)
                preorderTraversal(it.right)
            }
        }
        preorderTraversal(tree)
        w.flush()
    }
    this.close()

}


