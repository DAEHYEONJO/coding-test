package tree

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val height = readLine().toInt()
        val inorder = readLine().split(" ").map{it.toInt()}.toIntArray()
        val result = Array<ArrayList<Int>>(height){ArrayList()}
        fun inorderTraversal(depth: Int, start: Int, end: Int){
            if (depth == height) return
            val mid = (start+end).div(2)
            inorderTraversal(depth+1, start, mid)
            result[depth].add(inorder[mid])
            inorderTraversal(depth+1, mid+1, end)
        }

        inorderTraversal(0,0,inorder.size-1)

        result.forEach{
            w.write(it.joinToString(separator = " ",truncated = "",postfix = "\n"))
        }
        w.flush()
    }
}