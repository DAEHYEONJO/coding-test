package implementation
import java.util.*
import kotlin.math.*
data class Info(val alg: Int, val cop: Int, val time: Int)
class Solution {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        //problem[i]=문제푸는데알고력, 문제푸는데코딩력, 증가알고력, 증가코딩력, 문풀시간
        //문제안풀고 1높이는데  +1시간
        var maxAlg = 0
        var maxCop = 0
        problems.forEach{
            if(maxAlg < it[0]) maxAlg = it[0]
            if(maxCop < it[1]) maxCop = it[1]
        }
        var answer: Int = 0

        //problems.sortBy{it[4]}//문풀시간 작은 기준 sort
        val sb = StringBuilder()

        if(alp>=maxAlg && cop>=maxCop) return 0
        val pq = PriorityQueue<Info>(compareBy{it.time})
        pq.add(Info(alp,cop,0))
        while(pq.isNotEmpty()){
            println(pq)
            val(curAlg, curCop, time) = pq.poll()
            if(curAlg >= maxAlg && curCop >= maxCop){
                return time
            }
            val diffAlg = (maxAlg - curAlg)
            val diffCop = (maxCop - curCop)

            for(i in 0 until problems.size){
                val (_,_,ithAlg, ithCop, ithTime) = problems[i]
                if (diffAlg <= ithAlg){
                    pq.add(Info(curAlg+diffAlg , curCop, time + diffAlg))
                }else{
                    if(ithAlg!=0){
                        val problemCount = diffAlg/ithAlg
                        val algCount = ithAlg * problemCount
                        val copCount = ithCop * problemCount
                        val diffAlgDiff = diffAlg - algCount // 추가적으로 더 풀어야 하는 알고리즘 문제
                        val problemTimeAlg = problemCount*ithTime
                        if (diffAlg <= problemTimeAlg+diffAlgDiff) {
                            // 이 문제를 풀어서 알고리즘을 올리는것보다 그냥 올리는게 낫다
                            pq.add(Info(curAlg + diffAlg, curCop, time + diffAlg))
                        }else{
                            // 이 문제를 풀어서 알고리즘을 올리는게 더 낫다.
                            pq.add(Info(curAlg + diffAlg, curCop+copCount, time + problemTimeAlg+diffAlgDiff))
                        }
                    }
                }
                if (diffCop <= ithCop){
                    pq.add(Info(curAlg , curCop+diffCop, time + diffCop))
                }else{
                    if (ithCop!=0){
                        val problemCount = diffCop/ithCop
                        val copCount = ithCop*problemCount
                        val algCount = ithAlg*problemCount
                        val diffCopDiff = diffCop - copCount
                        val problemTimeCop = problemCount*ithCop
                        if(diffAlg<=problemTimeCop+diffCopDiff){
                            pq.add(Info(curAlg + diffCop, curCop, time + diffCop))
                        }else{
                            pq.add(Info(curAlg + algCount, curCop+diffCop, time + problemTimeCop+diffCopDiff))
                        }
                    }
                }

            }
        }

        return answer
    }
}

fun main() {
    val a = Array(2){IntArray(5)}
    a[0] = intArrayOf(10,15,2,1,2)
    a[1] = intArrayOf(20,20,3,3,4)
    println(Solution().solution(10,10,a))
}