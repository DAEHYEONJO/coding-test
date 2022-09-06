package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val adjList = Array(n+1){LinkedList<Int>()}
    val times = IntArray(n+1)
    val degrees = IntArray(n+1)
    val dp = IntArray(n+1){0}
    val queue = ArrayDeque<Int>()
    repeat(n){
        val st = StringTokenizer(readLine())
        val job = it+1
        val jobTime = st.nextToken().toInt()
        val adjCount = st.nextToken().toInt()
        times[job] = jobTime
        dp[job] = jobTime
        repeat(adjCount){
            val adjNode = st.nextToken().toInt()
            adjList[adjNode].add(job)
            degrees[job]+=1
        }
    }
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->

        for(i in 1 until n+1){
            if (degrees[i]==0) {
                queue.add(i)
            }
        }

        while(queue.isNotEmpty()){
            val node = queue.pollFirst()
            for(adjNode in adjList[node]){
                // 다음 작업(adjNode)를 수행해야 하는데 필요한 선행작업 (node) 가 끝나는 시간 + 다음작업을수행하는데 걸리는시간
                // maximum 을 계속 갱신해주면 된다.
                // 선행작업이 여러개인 경우 선행작업 중 제일 오래걸리는 작업이 끝나야 다음작업(adjNode)작업을 수행할 수 있으므로 max 값으로 갱신함.
                dp[adjNode] = dp[adjNode].coerceAtLeast(dp[node] + times[adjNode])
                if (--degrees[adjNode]==0){
                    queue.add(adjNode)
                }
            }
        }

        w.write("${dp.maxOrNull()}")
        w.flush()
    }
}