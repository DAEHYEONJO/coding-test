package greedy

import java.io.*
import java.util.*

data class Meeting(val startTime: Int, val endTime: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = Array(n){
        val st = StringTokenizer(readLine())
        Meeting(startTime = st.nextToken().toInt(), endTime = st.nextToken().toInt())
    }.sortedWith(compareBy({it.startTime},{it.endTime}))
    // 시작시간 오름차순 & 시작시간 같으면 끝나는시각 오름차순

    val pq = PriorityQueue<Meeting>(compareBy{-it.endTime})//endTime이 큰순으로 튀어 나오게 하기
    pq.add(a[0])
    for(i in 1 until a.size){ // 계속 startTime이 크거나 같은애들이 등장할거임
        val (curMeetingSt, curMeetingEt) = a[i]
        if (pq.isNotEmpty()){
            val (sT, eT) = pq.peek()
            if (curMeetingEt < eT){ // 현재 제일 늦게 끝나는 미팅보다 더 빨리 끝나는 미팅이 있으면 걔로 집어넣기
                pq.poll()
                pq.add(a[i])
            }else if(curMeetingSt>=eT){ // i 번째 미팅 시작시간이 큐에 있는 젤 늦게 끝나는 미팅보다 크거나 같으면 큐에 넣기
                pq.add(a[i])
            }
        }
    }
    println(pq.size)

}