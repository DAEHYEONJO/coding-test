package codeforces

import java.io.*

fun main () = with(BufferedReader(InputStreamReader(System.`in`))){

    val t = readLine()!!.toInt()
    var num : Long = 1L
    var ans : Long = 1L
    repeat(t){
        val x = readLine()!!
        val n = readLine()!!.toLong()
        for( i in 1 until x.length){
            when(x[i]){
                '0'->{
                    val temp = (num shl 1)
                    if(temp <=n){
                        num = temp
                        ans += 1
                        println("0 $i 번째 $temp $ans")
                    }
                }
                '1'->{
                    val temp = (num shl 1) + 1
                    if(temp <= n){
                        num = temp
                        ans += 1
                        println("1 $i 번째 $temp $ans")
                    }
                }
            }
        }
        println(ans)
    }

    /*println(Int.MIN_VALUE+Int.MIN_VALUE)
    println(Int.MIN_VALUE+(-1))
    println(Int.MAX_VALUE+(1))
    val n = readLine().toInt() // 2<=n<=100 000
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray()
    var m = 0
    var M = 0
    var mLI = 0
    var MLI = 0

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->

        var index = 0
        for(i in 1 until n){
            if (a[i]!=a[0]) {
                index = i
                break
            }
        }

        if (index == 0){
            w.write("$n")
            w.flush()
            return@with
        }

        //index는 최초로 a[0] 와 달라진 인덱스.
        if (a[0] < a[index]){
            m = a[0]; M = a[index]; mLI = index-1; MLI = index
        }else{
            m = a[index]; M = a[0]; mLI = index; MLI = index-1
        }

        var curLength = index+1 // 0 ~ index 까지의 길이 => 현재 가장 긴 길이
        //확인할 수 있는 구간에는 2개의 수만 있어야함.
        //index+1부터 확인하기.
        var ans = curLength
        for(j in index+1 until n){
            when{
                a[j] == m -> {
                    curLength++
                    mLI = j
                }
                a[j] == M ->{
                    curLength++
                    MLI = j
                }
                // 3 4 4 4 4 5
                // m       M a[j]
                // mLI     MLI
                a[j] == M+1 ->{ // 큰게 등장 했으므로 작은걸 없애줘야 함.
                    m = M
                    M = a[j]
                    curLength = j-mLI
                    mLI = MLI
                    MLI = j
                }
                // 4 3 3 3 3 2
                // M       m a[j]
                // MLI     mLI
                a[j] == m-1 ->{
                    M = m
                    m = a[j]
                    curLength = j-MLI
                    MLI = mLI
                    mLI = j
                }
            }
            ans = ans.coerceAtLeast(curLength)
        }
        w.write("$ans")
        w.flush()
    }*/


}