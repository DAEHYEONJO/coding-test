package graph

import java.io.*
import java.util.*
import kotlin.math.*

data class Pos(val y: Int, val x: Int)
fun getDist(src: Pos, dst: Pos): Int = abs(src.y - dst.y) + abs(src.x - dst.x)
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var t = readLine().toInt()
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        out@while(t-->0){
            val pyunCount = readLine().toInt()
            var st = StringTokenizer(readLine())
            val home = Pos(y = st.nextToken().toInt(), x = st.nextToken().toInt())
            val pyunPos = Array(pyunCount){
                st = StringTokenizer(readLine())
                Pos(y = st.nextToken().toInt(), x = st.nextToken().toInt())
            }
            val visited = BooleanArray(pyunCount)
            st = StringTokenizer(readLine())
            val festival = Pos(y = st.nextToken().toInt(), x = st.nextToken().toInt())
            val q = ArrayDeque<Pos>().apply {
                add(home)
            }
            while(q.isNotEmpty()){
                val curPos = q.pollFirst()
                if (getDist(curPos, festival) <= 1000){
                    w.write("happy\n")
                    continue@out
                }
                for (i in 0 until pyunCount){
                    val dist = getDist(curPos, pyunPos[i])
                    if (dist<=1000 && !visited[i]){
                        visited[i] = true
                        q.add(pyunPos[i])
                    }
                }
            }
            w.write("sad\n")
        }
        w.flush()
    }
}