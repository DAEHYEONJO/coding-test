package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val n = readLine().toInt()
        val lengths = readLine().trim().split(" ").map{it.toInt()}.toIntArray() // n-1 개
        val prices = readLine().trim().split(" ").map{it.toInt()}.toIntArray() // n 개
        var curPrice = prices.first()
        var answer = lengths.first()*curPrice.toLong()

        //리터당 기름값을 최소 비용으로 유지해 준다. 
        for (i in 1 until n-1){
            if (prices[i]<curPrice){
                curPrice = prices[i]
            }
            answer+=curPrice*lengths[i].toLong()
        }
        w.write("$answer")
        w.flush()
    }

}