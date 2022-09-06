package graph

import java.io.*
import java.util.*

val evenDirs = arrayOf(
    intArrayOf(-1, -1),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(1, 0),
    intArrayOf(1, -1),
    intArrayOf(0, -1),
)//y 짝수 방향 벡터
val oddDirs = arrayOf(
    intArrayOf(-1, 0),
    intArrayOf(-1, 1),
    intArrayOf(0, 1),
    intArrayOf(1, 1),
    intArrayOf(1, 0),
    intArrayOf(0, -1),
)//y 홀수 방향 벡터

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val (w, h) = readLine().split(" ").map { it.toInt() }
    val arr = Array(h+2){IntArray(w+2)}
    val noInsideZero = Array(h+2){BooleanArray(w+2)}
    val q = ArrayDeque<Pair<Int, Int>>()
    var answer = 0
    fun isValidForNoInsideZero(y: Int, x: Int): Boolean = (y in 0 .. h+1) && (x in 0 .. w+1)
    fun countNoInsideZero(y: Int, x: Int, dirs: Array<IntArray>): Int{
        var count = 0
        for((dy, dx) in dirs) {
            val newY = y + dy
            val newX = x + dx
            if (noInsideZero[newY][newX] ) {
                count++
            }
        }
        return count
    }

    // 입력 받기
    for(y in 1 until h+1){
        val st = StringTokenizer(readLine())
        for(x in 1 until w+1){
            arr[y][x] = st.nextToken().toInt()
        }
    }

    // 안에 있지 않은 0 좌표들 표시하기
    q.add(Pair(0,0))
    noInsideZero[0][0] = true
    while(q.isNotEmpty()){
        val (curY, curX) = q.pollFirst()
        val dirs = if(curY % 2 ==0) evenDirs else oddDirs
        for((dy, dx) in dirs){
            val newY = curY + dy
            val newX = curX + dx
            if(isValidForNoInsideZero(newY, newX) && arr[newY][newX]==0 && !noInsideZero[newY][newX]){
                noInsideZero[newY][newX] = true
                q.add(Pair(newY, newX))
            }
        }
    }

    // 1인 회색 마름모를 기준으로 안에 있지 않은 0좌표들 더해주기
    for (y in 1 .. h) {
        for (x in 1 .. w) {
            if (arr[y][x]==1) {
                answer += if (y%2 == 0){
                    countNoInsideZero(y, x, evenDirs)
                }else{
                    countNoInsideZero(y, x, oddDirs)
                }
            }
        }
    }

    println(answer)

}