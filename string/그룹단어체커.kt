package string

import java.io.*

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    var result = 0
    repeat(n){
        val line = readLine().trim()
        if (isGroupString(line)) result+=1
    }
    println(result)

}

fun isGroupString(str : String):Boolean{
    if(str.length==1) return true
    val appeared = HashSet<Char>(str.length)
    //i 번째 등장한 char가 연속해서 나오다가 다른 char가 나왔는데 다시 i번째 등장한 char가 등장하면 false

    for (i in 0 until str.length-1){
        if (str[i]!=str[i+1]){
            if (appeared.contains(str[i+1])) {
                return false
            }
            appeared.add(str[i])
        }
    }

    return true
}