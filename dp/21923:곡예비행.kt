package dp

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    //1000*1000*10000 => dp 배열 Long type 으로 해야함
    //(n-1, 0) 에서 상승비행, (n-1, m-1) 에서 하강비행으로 종료.
    //상승 -> 하강으로 변경시에는 상승비행 종료 칸에서 바로 하강비행 시작해야함.
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) { readLine().trim().split(" ").map { it.toLong() }.toLongArray() }
    val dpUp = Array(n) { LongArray(m) { -10000000001L } }
    dpUp[n - 1][0] = arr[n - 1][0]
    val dpDown = Array(n) { LongArray(m) { -10000000001L } }
    val dirUp = arrayOf(arrayOf(-1, 0), arrayOf(0, 1))//위, 우
    val dirDown = arrayOf(arrayOf(-1, 0), arrayOf(0, -1))//아래, 우 지만 n-1,m-1 에서부터 시작하므로 위, 왼쪽 으로 변경
    dpDown[n-1][m-1] = arr[n-1][m-1]

    // 위, 오
    // 현재 상승방향 -> 위, 오른쪽 갈 수 있음
    // 현재 하강 방향 -> 아래, 오른쪽 갈 수 있음
    // 언제 상승 -> 하강으로 바뀔지 모름. -> 상승 dp랑 하강 dp를 따로 관리해야함.
    // 상승 dp 는 우 & 상으로 갈 수 있음. -> max 값으로 갱신하기.

    fun isValid(y: Int, x: Int): Boolean = (y in (0 until n)) && (x in (0 until m))

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        // (n-1, 0) 에서부터 상승운동을 할 때 최댓값 갱신
        for (i in n - 1 downTo 0) {
            for (j in 0 until m) {
                for ((dy, dx) in dirUp) {
                    val newY = i + dy
                    val newX = j + dx
                    if (isValid(newY, newX)) {
                        dpUp[newY][newX] = dpUp[newY][newX].coerceAtLeast(dpUp[i][j] + arr[newY][newX])

                    }
                }
            }
        }

        // (n-1, m-1) 까지 하강 운동을 할 때 최댓값 갱신
        for(i in n-1 downTo 0){
            for(j in m-1 downTo 0){
                for ((dy, dx) in dirDown) {
                    val newY = i + dy
                    val newX = j + dx
                    if (isValid(newY, newX)) {
                        dpDown[newY][newX] = dpDown[newY][newX].coerceAtLeast(dpDown[i][j] + arr[newY][newX])
                    }
                }
            }
        }
        var ans = -10000000001L
        //(i,j) 까지 오는데 상승운동의 최댓값과, (i,j)에서 (n-1,m-1)까지 가는 하강운동의 최댓값을 더하는 것이다.
        //이 둘의 합은 (n-1,0)에서 (n-1,m-1)까지 상승 -> 하강 운동의 최댓값이다.
        for(i in 0 until n){
            for(j in 0 until m){
                ans = ans.coerceAtLeast(dpUp[i][j] + dpDown[i][j])
            }
        }
        w.write("$ans")
        w.flush()
    }
}

