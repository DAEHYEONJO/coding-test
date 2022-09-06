package tree

import java.io.*
import kotlin.math.pow

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val height = readLine().toInt()
        val inorder = readLine().split(" ").map{it.toInt()}.toIntArray()
        var root = (inorder.size-1).div(2)
        repeat(height){
            val count = 2.0.pow(it).toInt()//각 height 에서 몇 개씩 출력 되는지
            var iter = count
            val plusNum = 2.0.pow(height-it).toInt()
            var temp = root
            while (iter>0){
                w.write("${inorder[temp]} ")
                temp+=plusNum
                iter--
            }
            root -= 2.0.pow(height-it-2).toInt()
            w.write("\n")
        }
        w.flush()
    }
}