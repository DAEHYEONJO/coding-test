package string

import java.io.*

fun main():Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val str1 = readLine().trim()
    val str2 = readLine().trim()

    val array = Array<Array<Int>>(str2.length+1){Array<Int>(str1.length+1){0}}

    for (i in 1 until str2.length+1){
        for (j in 1 until str1.length+1){
            if (str1[j-1] == str2[i-1]){
                array[i][j] = array[i-1][j-1]+1
            }else{
                array[i][j] = array[i-1][j].coerceAtLeast(array[i][j-1])
            }
        }
    }
    println(array.last().last())
    var row = array.size-1
    var col = array[0].size-1
    var lcs = StringBuilder()
    while (array[row][col]!=0){
        //[row-1][col] or [row][col-1]에 [row][col]값과 같은 값이 있는지 찾기
        //있다면 row, col 값 update
        //없다면 row = row - 1 and col = col - 1 로 update -> 문자열 값 넣어주기
        if (array[row-1][col] == array[row][col]){ row = row-1 }
        else if (array[row][col-1] == array[row][col]){col = col -1}
        else{
            lcs.append(str1[col-1])
            row = row - 1
            col = col - 1
        }
    }
    println(lcs.toString().reversed())
}