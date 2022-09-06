import java.io.*
import kotlin.collections.ArrayDeque

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val n = readLine().toInt()
        val balloons = ArrayDeque<IntArray>()

        readLine().split(" ").map{it.toInt()}.forEachIndexed{ index, i ->
            balloons.add(intArrayOf(index+1, i))
        }
        var curIndex = 0
        while (balloons.isNotEmpty()){
            val curBalloon = balloons[curIndex]
            w.write("${curBalloon[0]} ")
            balloons.removeAt(curIndex)
            if (balloons.isNotEmpty()){
                curIndex = if (curBalloon[1] > 0){
                    (curIndex + curBalloon[1] -1).mod(balloons.size)
                }else{
                    (curIndex + curBalloon[1]).mod(balloons.size)
                }
            }
        }
        w.flush()
    }
}