package showmethecode

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    var n = readLine().toInt()
    val str =readLine().trim()
    var e =n-1
    val dp = IntArray(n){0}
    for (i in n-1 downTo 1){
        if(str[i]=='E'){
            dp[i]+=1
        }
        dp[i-1]+=dp[i]
    }
    var ans = 0L
    var eCont = 0
    var h =e
    var w =e

    for(i in n-3 downTo 0){

        if (str[i]=='H'){
            for(j in i-1 downTo 0){
                if (str[j]=='W'){
                    ans+=(1 shl dp[i+1] - (1+dp[i+1]))
                    ans%=1000000007
                }
            }
        }
    }

    println(ans)
}
