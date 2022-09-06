package codeforces

import java.io.*
import java.util.*
import kotlin.math.ceil

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    // 같은 그룹 멤버는 무조건 같은 택시를 타야함.
    // 3 3 2 가 있을 때, 2명짜리 그룹은 1명 1명 쪼개져서 탈 수 없음.
    val n = readLine().toInt() // 1 <= n <= 10^5
    val s = IntArray(5){0}

    val st = StringTokenizer(readLine())
    repeat(n) {
        val num = st.nextToken().toInt()
        s[num] += 1
    }


    var ans = s[4]
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        ans+=s[3]
        s[1]-=minOf(s[3], s[1]) // 3명과 1명을 짝지어서 태우기, 3명 조합보다 1조합이 적어도 3명은 합칠수 있는 사람이 없으므로 s[3] 다 더함
        ans+=(s[2]/2)//두명씩 짝지워 태우기
        s[2]%=2//1 or 0
        if (s[2]==1){
            ans+=1//두명과 한명 짝짓기
            s[1]-= minOf(2, s[1])//한명이 0,1,2 명 있는 경우 짝지어서 태웠음.
        }
        ans+=(s[1]/4)
        s[1]%=4
        if (s[1]>0) ans+=1

        w.write("$ans")
        w.flush()
    }
}