package math

import java.io.*
import kotlin.math.*
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (k, m) = readLine().split(" ").map{it.toInt()}
    val maxNum = 10.0.pow(k).toInt()
    val primeList = ArrayList<Int>()
    val isPrime = BooleanArray(maxNum){true}
    val isMadeByTwoPrime = BooleanArray(maxNum){false}
    val isDivideByTwoPrime = BooleanArray(maxNum){false}
    isPrime[1] = false
    for (i in 2 until maxNum){
        if (isPrime[i]){
            primeList.add(i)
            for(j in i+i until maxNum step i){
                isPrime[j] = false
            }
        }
    }

    for (i in 0 until primeList.size-1){
        for (j in i+1 until primeList.size){
            val sum = primeList[i]+primeList[j]
            if (sum<maxNum) isMadeByTwoPrime[sum] = true
        }
    }

    primeList.forEachIndexed { index, i ->
        for (j in index until primeList.size){
            val lI = i.toLong()
            val lJ = primeList[j].toLong()
            val multiply = lI*lJ
            if (multiply<maxNum) isDivideByTwoPrime[multiply.toInt()] = true
        }
    }

    fun isRemainderTwoPrime(x: Int): Boolean{
        var localX = x
        while (localX.mod(m)==0){
            localX /= m
        }
        return isDivideByTwoPrime[localX]
    }
    val nums = IntArray(k){0}
    var count = 0
    fun getNums(nums: IntArray, depth: Int, visited: BooleanArray){
        if (depth == k) {
            nums.joinToString(separator = "").also{
                if (it=="0") return
                if (isMadeByTwoPrime[it.toInt()] && isRemainderTwoPrime(it.toInt())){
                    count++
                }
            }
            return
        }
        val start = if (depth==0) 1 else 0
        for (i in start .. 9){
            if (visited[i]) continue
            nums[depth] = i
            visited[i] = true
            getNums(nums, depth+1,visited)
            visited[i] = false
        }
    }
    getNums(nums, 0, BooleanArray(10){false})
    println(count)
}