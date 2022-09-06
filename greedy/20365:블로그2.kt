package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val line = readLine().trim()

    // 다 한 색으로 쭉 칠하고 다른색이 등장할 경우 페인트를 칠해준다.
    fun getPaintCount(color: Char): Int{
        var answer = 1
        var index = 0
        while (index<n){
            if (line[index]!=color){
                var temp = index
                while (temp<n && line[temp]==line[index]){
                    temp+=1
                }
                index = temp
                answer+=1
            }else{
                index+=1
            }
        }
        return answer
    }

    println(getPaintCount('B').coerceAtMost(getPaintCount('R')))

}

