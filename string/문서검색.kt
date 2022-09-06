package string

import java.io.*

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val doc = readLine().trim()
    val word = readLine().trim()

    var i = 0
    var result = 0
    while(i!=doc.length){

        if (doc[i] == word.first() && (i+word.length<=doc.length)){
            if (doc.substring(i,i+word.length) == word){
                i+=word.length
                result+=1
                continue
            }
        }

        i+=1

    }
    println(result)
}
//abcdcab
//abc
//i = 0 -> 3