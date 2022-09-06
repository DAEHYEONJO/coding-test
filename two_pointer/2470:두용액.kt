package two_pointer

import java.io.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()

    println(-(a.binarySearch(1)))
    var ans1 = 1000000000
    var ans2 = 1000000000
    var diff = ans1+ans2
    // sorting 된 원소를 하나씩 돌면서 합이 가장 0에 가까운 원소 하나 찾기
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->

        var s = 0
        var e = n-1
        while(s<e){
            val temp = abs(a[s] + a[e])
            if (diff>temp){
                diff = temp
                ans1 = a[s]
                ans2 = a[e]
            }
            if (temp == 0) break
            if (abs(a[s]) > abs(a[e])){
                s+=1
            }else{
                e-=1
            }
        }

        if (ans2 < ans1){
            ans1 = ans2.also { ans2 = ans1 }
        }
        w.write("$ans1 $ans2")
        w.flush()

    }
}