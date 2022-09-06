package dp

import java.io.*
import java.util.*
// 2차원 배열 누적합은 row 누적합을 먼저 구한다.
// 다음 row 누적합 배열에 대하여 col 누적합을 구한다.
// 2차원 누적합 배열에서 dp[row][col]이 의미하는 것은
// [0][0]   [0][col]
// ...          ...
// [row][0] [row][col]
// 해당 직사각형 사이의 모든 원소를 다 더한것에 해당한다.
// 따라서 (y1, x1)과 (y2, x2) 범위 직사각형의 누적합을 구하고 싶은 경우
// (y2, x2) - (y2, x1-1) - (y1-1, x2) + (y1-1, x1-1)
// 을 해주면 된다. 뒤에 더해주는건 두번 빼버린 범위에 대한 교집합을 한번 더해주는 것이다.

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val(n, m) = readLine().trim().split(" ").map{ it.toInt() }
        val dp = Array(n+1){LongArray(n+1){0L} }
        /*repeat(n){ i->
            val st = StringTokenizer(readLine())
            repeat(n){ j->
                dp[i+1][j+1] = (st.nextToken().toLong()) + dp[i+1][j]
                //row 누적합 구하기
            }
        }

        //row 누적합에 대한 col 누적합 구하기
        repeat(n){ i ->
            repeat(n){ j ->
                dp[j+1][i+1]+=dp[j][i+1]
            }
        }*/
        // 2*N^2 -> N^2으로 개선 버전
        // dp[i+1][j+1] 은 마찬가지로 (0,0)~(i+1, j+1) 직사각형 값들의 모든 합이다.
        // dp[i+1][j]랑 dp[i][j+1]을 더해준다면 dp[i][j] 가 전자의 교집합이다.
        // 이것을 두번 더해주게 되므로 한번 빼주고
        // 현재 위치의 value 를 더해주면 누적합이 된다.
        repeat(n){ i ->
            val st = StringTokenizer(readLine())
            repeat(n){ j ->
                dp[i+1][j+1] = dp[i][j+1] + dp[i+1][j] - dp[i][j] + st.nextToken().toLong()
            }
        }
        repeat(m){
            val st = StringTokenizer(readLine())
            val y1 = st.nextToken().toInt()
            val x1 = st.nextToken().toInt()
            val y2 = st.nextToken().toInt()
            val x2 = st.nextToken().toInt()
            w.write("${dp[y2][x2]-dp[y2][x1-1]-dp[y1-1][x2]+dp[y1-1][x1-1]}\n")
        }
    }
}