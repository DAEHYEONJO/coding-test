package heap


import java.io.*
import java.util.*

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    fun solution(jobs: Array<IntArray>): Int {
        var answer = 0
        //우선순위1: 작업 요청시간
        jobs.sortBy { it[0] }//제일 빠른 요청시간의 작업 찾기
        //우선순위2 (소요시간)
        val pq = PriorityQueue<IntArray> { o1, o2 ->
            o1[1] - o2[1]
        }


        var curTime = 0 // 현재시간
        var sIndex = 0
        var totalTime = 0
        while(pq.isNotEmpty() || sIndex<jobs.size){

            // 특정 작업이 진행되는 동안 요청된 작업이 있는지 check
            while (sIndex<jobs.size && jobs[sIndex][0]<=curTime){
                pq.add(jobs[sIndex++])
            }

            if (pq.isEmpty()){
                // 없으면 현재 진행 작업 이후 요청 들어온 작업 찾고 curTime 을 요청시간으로 update 해주기
                curTime = jobs[sIndex][0]
            }else{
                val(requestTime, takeTime) = pq.poll()
                totalTime += (curTime - requestTime) + takeTime
                curTime += takeTime

            }
        }
        return totalTime.div(jobs.size)
    }

    val array = Array<IntArray>(3){IntArray(2) }
    array[0] = intArrayOf(0,3)
    array[1] = intArrayOf(1,9)
    array[2] = intArrayOf(2,6)

    println(solution(array))
}