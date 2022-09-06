package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, m) = readLine().trim().split(" ").map{ it.toInt() }
    val arr = Array(n+1){ IntArray(m+1) }
    repeat(n){ i ->
        val st = StringTokenizer(readLine())
        repeat(m){ j ->
            arr[i+1][j+1] += arr[i][j+1] + arr[i+1][j] - arr[i][j] + st.nextToken().toInt()
        }
    }
    val k = readLine().toInt()

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        repeat(k){
            val st = StringTokenizer(readLine())
            val y1 = st.nextToken().toInt()
            val x1 = st.nextToken().toInt()
            val y2 = st.nextToken().toInt()
            val x2 = st.nextToken().toInt()
            w.write("${arr[y2][x2] - arr[y2][x1-1] - arr[y1-1][x2] + arr[y1-1][x1-1]}\n")
        }
        w.flush()
    }
    this.close()
}