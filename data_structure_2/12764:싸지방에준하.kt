package data_structure_2

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
    val curUsed = PriorityQueue<Pair<Int, Int>>(compareBy({it.first}, {it.second}))//end시간, computer 번호
    val computerUsed = IntArray(n){0}
    var totalComputers = 1 // 초기 0 번 컴터는 무조건 있음
    val emptyComputers = PriorityQueue<Int>()
    repeat(n){
        val (start, end) = readLine().split(" ").map{it.toInt()}
        pq.add(Pair(start, end))
    }

    out@while (pq.isNotEmpty()){
        val(startTime, endTime) = pq.poll()
        if (curUsed.isEmpty()){
            //0번 pc 사용 가능
            curUsed.add(Pair(endTime, 0))
            computerUsed[0]++
        }else{
            if(curUsed.peek().first <= startTime){
                //이미 시작한 사람들중, 현재 시작하려는 사람보다 먼저 시작해서 끝난 컴퓨터가 있는 경우 -> 빈 컴터 queue 에 넣어주기
                while (curUsed.isNotEmpty() && curUsed.peek().first <= startTime){
                    emptyComputers.add(curUsed.poll().second)
                }
            }
            if (emptyComputers.isEmpty() && curUsed.peek().first > startTime){
                // 현재 사용중인 컴퓨터들중 젤 빨리 끝나는 컴퓨터보다 지금 시작하려는 사용자의 시작 시간이 작고, 모든 컴퓨터들이 사용중이므로 새로 한대 만들기
                totalComputers++
                computerUsed[totalComputers-1]++
                curUsed.add(Pair(endTime, totalComputers-1))
                continue@out
            }
            // 현재 사용자 빈 컴퓨터중 제일 컴퓨터 번호가 작은 자리에서 시작시켜주기
            val emptyComputerNum = emptyComputers.poll()
            computerUsed[emptyComputerNum]++
            curUsed.add(Pair(endTime, emptyComputerNum))
        }
    }
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        w.write("${totalComputers}\n")
        w.write(computerUsed.joinToString(separator = " ", limit = totalComputers, truncated = ""))
        w.flush()
    }
}