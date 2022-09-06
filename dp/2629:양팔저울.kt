package dp

import java.io.*
import kotlin.math.abs
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // 1<=n<=30
    // 1<=choo[i]<=500
    // 1<=구슬 수(m)<=7
    // 1<=구슬무게<=40000
    val n = readLine().toInt()
    val choo = IntArray(1) + readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    val m = readLine().toInt()
    val dp = Array(n + 1) { BooleanArray(40001) { false } } // dp[i][j] -> i번째 추까지 사용해서 j 무게 구슬을 만들수 있냐?
    dp[0][0] = true // 아무것도 사용하지 않고 0키로 만들기 가능.
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        for(i in 1 until n+1){
            for(j in 0 until 40001){
                // dp[i-1][j] 가 true 면 i-1 번째 추까지 사용해서 j 라는 무게의 추를 만드들 수 있음
                // i 번째 추를 안쓰면 dp[i][j] = true 다.
                // 추와 구슬은 섞어서 쓸 수 있다.
                // 4kg, 1kg 추가 있고 3kg 구슬을 만든다고 생각해보자.
                // 1,3 / 4 이렇게 추를 만들 수 있다.
                // dp[i-1][abs(choo[i]-j)] 가 true 라면 abs(choo[i]-j) 무게가 있는 저울에 j를 놓고 반대편 저울이 choo[i]를 놓으면 된다.
                // 따라서 dp[i][abs(choo[i]-j)] 가 true 면 dp[i][j] 도 true 다.
                // 반대 논리로 dp[i-1][choo[i]+j] 가 true 라면 choo[i]+j 가 왼쪽 저울에 있는 경우 j 무게의 구슬과 choo[i] 무게의 추를 오른쪽 저울로 옮기면 무게가 동등해진다.
                // 따라서 dp[i-1][choo[i]+j] 가 true 면 dp[i][j]도 true 다.
                if(dp[i-1][j]){
                    dp[i][j] = true
                }
                else if (abs(choo[i]-j) < 40001 && dp[i-1][abs(choo[i]-j)]){
                    dp[i][j] = true
                }
                else if ((choo[i]+j) < 40001 && dp[i-1][choo[i]+j]){
                    dp[i][j] = true
                }
            }
        }
        val st = StringTokenizer(readLine())
        repeat(m){
            w.write(if (dp[n][st.nextToken().toInt()]) "Y " else "N ")
        }
        w.flush()
    }
}