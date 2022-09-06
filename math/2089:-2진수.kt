package math

import java.io.*

fun main()  = with(BufferedReader(InputStreamReader(System.`in`))){

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->

        //-13 = -2 * 7 + 1
        // 7 = -2 * -3 + 1
        // -3 = -2 * 2 + 1
        // 2 = -2 * -1 + 0
        // -1 = -2 * 1 + 1
        // 1 = -2 * 0 + 1
        /// 0 -> 끝
        val sb = StringBuilder()

        var n = readLine().toInt()
        if (n==0) sb.append("0")
        while(n!=0){
            if (n%2==0){
                sb.append("${n%-2}")
                n/=-2
            }else{
                if (n>0){
                    sb.append("${n%-2}")
                    n/=-2
                }
                else{
                    sb.append("${-1*(n%-2)}")
                    n/=-2
                    n+=1
                }
            }
        }
        w.write(sb.toString().reversed())
        w.flush()
    }
}