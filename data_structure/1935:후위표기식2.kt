package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()

    val expression = readLine().trim()
    val nums = DoubleArray(n) { readLine().toDouble() }
    val stack = LinkedList<Double>()
    expression.forEach{
        if (it.isLetter()) stack.add(nums[it-'A'])
        else{
            val n1 = stack.pollLast()
            val n2 = stack.pollLast()
            stack.add(when(it){
                '+'->n2+n1
                '-'->n2-n1
                '*'->n2*n1
                else->n2/n1
            })
        }
    }
    println(String.format("%.2f",stack.poll()))
}
