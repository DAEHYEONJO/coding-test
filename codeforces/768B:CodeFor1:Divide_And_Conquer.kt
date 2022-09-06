package codeforces

import java.io.*
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, l, r) = readLine().trim().split(" ").map { it.toLong() }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        fun solve(num: Long, l: Long, r: Long): Long{
            var temp = num/2
            var length = 1L
            while(temp>0){
                length = length*2 + 1
                temp/=2
            }

            val mid = (length shr 1) + 1L
            if (l==1L && r==length) return num // 전체 길이인 경우 1의 갯수는 n 개
            if (l > mid) return solve(num/2, l-mid, r-mid)
            if (r < mid) return solve(num/2, l, r)
            if (mid == l && mid == r) return num%2
            if (l==mid) return solve(num/2, 1, r-mid) + num%2
            if (r==mid) return solve(num/2, l, mid -1) + num%2
            return solve(num/2, l, mid -1) + num%2 + solve(num/2, 1, r-mid)

        }



        w.write("${solve(n,l,r)}")
        w.flush()
    }

}