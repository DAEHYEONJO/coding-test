package string

import java.io.*
fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    val(l, c) = readLine().split(" ").map{it.toInt()}
    val list = readLine().split(" ").sorted()

    val moSet = HashSet<Char>().apply {
        add('a')
        add('e')
        add('i')
        add('o')
        add('u')

    }
    //최소 모음 1개 & 최소 자음 2개 이상의 길이 c인 string 을 만들어서 사전 순으로 print

    fun backTracking(depth : Int, index : Int, subStr : StringBuilder){

        if (depth == l) {
            val str = subStr.toString()
            var moCount = 0
            var jaCount = 0
            str.forEach {
                if (moSet.contains(it)) moCount+=1
                else jaCount+=1
            }
            if (moCount>=1 && jaCount>=2){
                println(str)
                return
            }
        }
        for (i in index until c){
            backTracking(depth+1, i+1, subStr.append(list[i]))
            subStr.deleteCharAt(subStr.lastIndex)
        }

    }
    backTracking(0, 0, StringBuilder())
}