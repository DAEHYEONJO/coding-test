package two_pointer

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val (n, k) = readLine().trim().split(" ").map{ it.toInt() }
        val oddPrefixCount = IntArray(n+1){0}
        val st = StringTokenizer(readLine())
        repeat(n){ i ->
            val num = st.nextToken().toInt()
            if (num%2 != 0) {
                oddPrefixCount[i+1] +=1
            }
            oddPrefixCount[i+1]+=oddPrefixCount[i]
        }

        var ans = 0
        var start = 1
        var end = 1
        while(end in start..n){
            val totalLength = end - start + 1
            val oddCount = oddPrefixCount[end] - oddPrefixCount[start - 1]
            if (oddCount<=k){
                end+=1
            }else{
                start += 1
            }
            ans = maxOf(ans, totalLength - oddCount)
        }
        w.write("$ans")
        w.flush()
    }

}