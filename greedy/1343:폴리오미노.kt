package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val table = readLine().trim()
    val size = table.length
    var n = 0
    val result = StringBuilder()
    while (n<size){
        if (n+4<=size && table.substring(n, n+4) =="XXXX"){
            result.append("AAAA")
            n+=4
        }else if (n+2<=size && table.substring(n, n+2) =="XX"){
            result.append("BB")
            n+=2
        }else{
            var dotCount = 0
            if (table[n]=='.'){
                for (i in n until table.length) {
                    if (table[i]=='.') {
                        dotCount+=1
                        result.append(".")
                    }else {
                        break
                    }
                }
            }else {
                break
            }
            n+=dotCount
        }
    }
    if (result.toString().length!=table.length) println(-1)
    else println(result.toString())
}