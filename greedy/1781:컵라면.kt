package greedy

import java.io.*
import java.util.*

data class Problem(val deadLine: Int, val ramen: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // 데드라인이 짧은 순서대로 먼저 숙제를 진행하는것이 좋음.
    val n = readLine().toInt()
    val problem = Array(n) {
        val st = StringTokenizer(readLine())
        Problem(st.nextToken().toInt(), st.nextToken().toInt())
    }
    // 데드라인 오름차순, 라면 내림차순
    problem.sortWith(compareBy({ it.deadLine }, { -it.ramen }))
    var curDay = 0
    var ans = 0
    val pq = PriorityQueue<Int>() // ramen 값 넣을 큐
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        problem.forEach { (deadLine, ramen) ->
            if (curDay < deadLine){
                // 데드라인보다 현재 날짜가 작으면 그냥 라면 넣기 ( 어차피 라면은 내림차순으로 소팅됨 )
                pq.add(ramen)
                curDay++
            }else{
                if (pq.isNotEmpty()){
                    val pqMinRamen = pq.peek()
                    if(pqMinRamen < ramen){ //만약에 데드라인이 더 짧은 과제를 먼저 했었는데, 데드라인이 더 긴 과제에서 더 많은 컵라면을 얻을 수 있다면
                        if (curDay <= deadLine){
                            pq.poll()
                            pq.add(ramen)
                        }
                        //큐에서 팝해주고 (데드라인이 더 짧았던 과제), 현재꺼 넣어줌 (데드라인이 더 길지만 더 많은 컵라면 얻을수 있음.)
                    }
                }
            }
            //w.write("$pq\n")
        }
        while (pq.isNotEmpty()) {
            ans += pq.poll()
        }
        w.write("$ans")
        w.flush()
    }
}

