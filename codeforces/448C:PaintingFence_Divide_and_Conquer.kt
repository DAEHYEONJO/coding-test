package codeforces

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    val max = n
    //제일 낮은 높이까지 다 가로로 한번씩 칠할 수 있다.

    fun solve(start: Int, end: Int): Int{

        // a 배열의 start 와 end 범위에서 가로로 쭉 칠할 수 있는 높이의 최솟값 찾기
        var ans = 0
        var min = 1000000001
        for(i in start until end){
            min = min.coerceAtMost(a[i])
        }

        for(i in start until end){
            a[i]-=min
        }
        ans = min // 가로로 쭉 칠할 수 있는 제일 낮은 높이값으로 답 일단 세팅

        // start 와 end 범위 내에 연속적으로 붙어있는 높이가 0이 아닌 지점 찾기.
        var s = start //최초로 높이가 0이 아닌 곳을 만나는 시작 Index
        var e = start //높이가 0이 아닌 곳을 만나다가 높이가 0인곳을 처음으로 만나게 되는 Index

        while(e<end){
            while(s<end && a[s]==0) s++
            e = s
            while(e<end && a[e]!=0) e++
            if (e!=s) ans+=solve(s,e)
        }

        return (end-start).coerceAtMost(ans)
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        w.write("${solve(0,n)}")
        w.flush()
    }

}
