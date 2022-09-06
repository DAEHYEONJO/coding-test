package greedy

import java.io.*

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    // dnaList 의 j번째 col 에 가장 많이 등장한 문자가 최종 정답 str 의 j번째 char 가 된다.
    // -> 각 row 의 문자들과 hamming distance 계산시 최대한 많이 겹쳐야 하므로
    // distance 는 sum j = 0 ~ m-1 (n - j번째 col 에 가장 많이 등장한 문자 수)
    // ->
    val (n ,m) = readLine().split(" ").map{it.toInt()}

    var dnaList = Array<String>(n){""}
    repeat(n){
        dnaList[it] = readLine().trim()
    }

    val colDiffCountMap = HashMap<Char, Int>()
    val resultStr = StringBuilder()
    var resultNum = 0
    repeat(m){ col ->
        repeat(n){ row ->
            colDiffCountMap.run {
                put(dnaList[row][col], this.getOrDefault(dnaList[row][col], 0)+1)
            }
        }

        var maxValue = 0
        var maxChar = 'x'
        colDiffCountMap.toSortedMap().forEach {
            //println("key : ${it.key} value : ${it.value}")
            if (maxValue<it.value){
                maxValue = it.value
                maxChar = it.key
            }
        }
        //println("------------------------------")
        resultStr.append(maxChar)
        resultNum+=(n-maxValue)

        colDiffCountMap.clear()
    }

    println(resultStr.toString())
    println(resultNum)


}