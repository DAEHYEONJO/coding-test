package dp

import java.io.*
import java.util.*
import java.math.BigInteger

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    val nums = IntArray(n - 1)
    repeat(n - 1) { i ->
        nums[i] = st.nextToken().toInt()
    }
    val answer = st.nextToken().toInt()
    //마지막 두 숫자 사이에 = 를 넣고 중간에 + or - 를 넣어서 등식이 성립하도록 할 수 있는 등식의 경우의수 구하기
    //등식을 왼쪽에서부터 계산할 때 중간 결과값이 0 ~ 20 사이만 나와야함.
    //음수 또는 21 이상이 나오게 된다면 올바르지 않은 등식에 해당한다.
    //입력으로 주어지는 숫자는 0~9 한자리수에 해당한다.
    //답은 2^63-1 이하다. -> long 으로 표현 가능함
    //dp 배열의 row 는 nums 에서 주어진 숫자의 index 다.
    //dp 배열의 col 은 중간에 계산해서 나올 수 있는 숫자다.
    //dp[row][col] 은 row 번째 숫자 까지 사용해서 col 이라는 값이 나올 수 있는 경우의 수다.
    //점화식은 dp[i][j-nums[i]] += dp[i-1][j] => i-1 번째 까지 사용해서 나올 수 있는 값인 j 라는 숫자에
    // j 에서 i 번째 숫자를 빼서 0 .. 20 사이의 숫자가 나온다면 i-1 번째 까지 사용해서 나오는 j 라는 수가 나오는 경우의 수를
    // i 번째 숫자를 사용해서 j 가 나올때의 경우의수인 dp[i][j-nums[i]] 에 더해주면 된다.
    // 덧셈도 마찬가지다.
    val dp = Array(n - 1) { LongArray(21) { 0L } }
    dp[0][nums.first()] = 1L
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        for(i in 1 until n-1){
            var sum = 0L
            for(j in 0 until 21){
                if (dp[i-1][j]!=0L){
                    if (j-nums[i] in (0 .. 20)){
                        dp[i][j-nums[i]]+=dp[i-1][j]
                    }
                    if (j+nums[i] in (0 .. 20)){
                        dp[i][j+nums[i]]+=dp[i-1][j]
                    }
                }
            }
        }
        w.write("${dp.last()[answer]}")
        w.flush()
    }


}