package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val nums = IntArray(n).apply {
        var i = 0
        repeat(n){
            this[i++] = readLine().toInt()
        }
    }
    var pushNum = 1
    var numsIndex = 0
    val sb = StringBuilder()
    val stack = LinkedList<Int>()

    while (numsIndex<n) {
        while (pushNum <= nums[numsIndex]){
            stack.add(pushNum++)
            sb.append("+\n")
        }
        if (stack.peekLast() > nums[numsIndex]) break
        else{
            while (numsIndex<n && stack.peekLast() ==nums[numsIndex]){
                stack.pollLast()
                numsIndex++
                sb.append("-\n")
            }
        }
    }
    if (numsIndex == n) println(sb.deleteAt(sb.lastIndex).toString())
    else println("NO")
}