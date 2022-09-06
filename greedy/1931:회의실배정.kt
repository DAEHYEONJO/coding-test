package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w->
        val n = readLine().toInt()
        val times = Array<IntArray>(n){readLine().trim().split(" ").map { it.toInt() }.toIntArray()}.sortedWith(
            compareBy({it[0]},{it[1]})
        )
        var curMeeting = times[0]
        var count = 1
        for (i in 1 until n){
            if (curMeeting[1]>times[i][1]){
                curMeeting=times[i]
            }else if(curMeeting[1]<=times[i][0]){
                curMeeting = times[i]
                count+=1
            }
        }
        w.write("$count")
        w.flush()
    }
}
