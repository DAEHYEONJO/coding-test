package implementation

import java.io.*
import java.util.*

fun main( ) = with(BufferedReader(InputStreamReader(System.`in`))){
    val m = HashMap<Int, Pair<Int,Int>>()
    val a = Array(5){ i ->
        val line = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
        line.forEachIndexed{ j, num ->
            m[num] = Pair(i, j)
        }
        line
    }


    fun getBingoCount():Int{
        var ans = 0
        var sum = 0
        for(i in 0 until 5){
            sum = 0
            for(j in 0 until 5){
                sum+=a[i][j]
            }
            sum.let { if (it==0) ans++ }
        }

        for(j in 0 until 5){
            sum = 0
            for(i in 0 until 5){
                sum+=a[i][j]
            }
            sum.let { if (it==0) ans++ }
        }

        sum = 0
        for(i in 0 until 5){
            sum+=a[i][i]
        }
        sum.let { if (it==0) ans++ }

        sum = 0
        for(i in 0 until 5){
            sum+=a[i][4-i]
        }
        sum.let { if (it==0) ans++ }

        return ans
    }

    repeat(5){ i ->
        val line = readLine().trim().split(" ").map{it.toInt()}.toIntArray()
        line.forEachIndexed { j, num ->
            val (y, x) = m[num]!!
            a[y][x] = 0
            if (getBingoCount()>=3){
                println(5*i + (j+1))
                return@with
            }
        }
    }
}