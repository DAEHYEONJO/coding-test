package dp

import java.io.*
import java.util.*
//이 문제의 핵심은 주어진 수열에서 최대 k개의 홀수를 제거했을 때, 짝수로 이뤄진 연속한 부분수열 중 가장 긴 길이가 뭔지 구하는 것이다.
//누적합을 이용해서 i 번째까지의 홀수 개수를 저장해두자.
// binary search 를 통해서 전체 범위에 있는 홀수의 개수를 k 개 이하로 컨트롤 해줄것이다.
// 원리는 다음과 같다.
// 수열을 전제범위로 잡자(전체범위 마지막 index = i)
// 전제범위의 수열에서 첫번째로 확인할 범위는 i - mid + 1 개수의 mid 포함 오른쪽 범위의 수열이다.
// mid ~ end 사이에 있는 홀수 개수가 k개 이하라면 mid를 왼쪽으로 보내도 된다.(수열의 길이를 늘려도 된다는 말이다.)
// 즉 end = mid - 1로 세팅.
// 반대라면 start = mid + 1 로 세팅하여 컨트롤하는 수열의 길이를 줄여준다.
// 이렇게 i = 1 .. n 까지 바꿔가면서 최장길이를 갱신해준다.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, k) = readLine().trim().split(" ").map{ it.toInt() }
    val arr = IntArray(n+1){0}
    val oddSum = IntArray(n+1){0}
    val st = StringTokenizer(readLine())
    repeat(n){
        arr[it+1] = st.nextToken().toInt()
    }

    for (i in 1 until n+1){
        oddSum[i] += oddSum[i-1]
        if (arr[i].mod(2) == 1) oddSum[i]+=1
    }

    var ans = 0
    for (i in 1 until n+1){
        var start = 1
        var end = i
        while(start<=end){
            val mid = (start+end)/2
            val totalLen = i-mid+1
            val oddCount = oddSum[i]-oddSum[mid-1]
            if (oddCount <= k){
                val temp = totalLen-oddCount
                ans = ans.coerceAtLeast(temp)
                end = mid - 1
            }else{
                start = mid + 1
            }
        }
    }
    println(ans)

}