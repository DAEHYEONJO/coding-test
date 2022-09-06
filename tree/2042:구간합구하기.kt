package tree

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, m, k) = readLine().split(" ").map{ it.toLong() }
    val arr = LongArray((n+1).toInt())
    val tree = LongArray((2*(n+1)).toInt())


    fun update(node: Int, value: Long){
        var curNode = node
        while(curNode <= n){
            tree[curNode] += value
            curNode += (curNode and -1*curNode)
        }
    }

    fun getSum(node: Int): Long{
        var curNode = node
        var sum : Long = 0L
        while(curNode > 0){
            sum += tree[curNode]
            curNode -= (curNode and -1*curNode)
        }
        return sum
    }

    repeat(n.toInt()){ i ->
        arr[i+1] = readLine().toLong()
        update(i+1, arr[i+1])
    }

    fun getIntervalSum(n1: Int, n2: Int): Long{
        return getSum(n2) - getSum(n1-1)
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        repeat((m+k).toInt()){
            var (a, b, c) = readLine().split(" ").map{ it.toLong() }
            when(a){
                1L -> {
                    update(b.toInt(), c.toLong() - arr[b.toInt()])
                    arr[b.toInt()] = c.toLong()
                }
                2L -> {
                    w.write("${getIntervalSum(b.toInt(), c.toInt())}\n")
                }
            }
        }
        w.flush()
        w.close()
        this.close()
    }


}