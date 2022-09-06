package tree

import java.io.*
import java.util.*



fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        data class Node(val key: Int, var left: Node? = null, var right: Node? = null)
        val t = readLine().toInt()
        repeat(t) {
            val n = readLine().toInt()
            val preorder = readLine().trim().split(" ").map { it.toInt() }.toIntArray()
            val root = preorder[0]
            val inorder = IntArray(n){0}
            val st = StringTokenizer(readLine())
            val inorderMap = HashMap<Int, Int>()
            repeat(n){
                inorder[it] = st.nextToken().toInt()
                inorderMap[inorder[it]] = it
            }
            var i = 0
            fun getTree(start: Int, mid: Int, end: Int): Node?{
                if (start>end || i == n-1){
                    --i
                    if (start<=end) return Node(inorder[mid])
                    return null
                }
                val node = Node(inorder[mid])
                node.left = getTree(start, inorderMap[preorder[++i]]!!, mid - 1)
                node.right = getTree(mid+1, inorderMap[preorder[++i]]!!, end)
                return node
            }

            fun postorderTraversal(root: Node?){
                root?.let { postorderTraversal(root.left) }
                root?.let{postorderTraversal(root.right)}
                root?.let { w.write("${it.key} ") }
            }
            val rootNode = getTree(0, inorderMap[root]!!, n-1)

            postorderTraversal(rootNode)
            w.write("\n")
        }
        w.flush()
        this.close()
    }

}