package graph

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    // 1 제외한 양수 와 음수 나누기
    // 1 제외한 양수 개수 -> 짝수 -> 큰거부터 2개씩 곱해서 더해주기 + 1개수
    // 1 제외한 양수 개수 -> 홀수  -> 젤 마지막에 1추가해주고 2개씩 곱해서 더해주기(짝수개 만들어주기) + 1개수
    // 음수 개수 -> 짝수 -> 큰거부터 2개씩 곱해주기
    // 음수 개수 -> 홀수 -> & 0 개수 0개 -> 마지막에 1 추가해서 큰거부터 2개씩 곱해서 더해주기 (제일 작은 음수 그냥 더해주는 효과)
    // 음수 개수 -> 홀수 -> & 0 개수 1개 이상 -> 제일 작은 음수 0이랑 곱해 없애주기
    val n = readLine().toInt()
    var posList = ArrayList<Int>()
    var negList = ArrayList<Int>()
    var zeroCount = 0
    var oneCount = 0
    repeat(n){
        val cur = readLine().toInt()

        if (cur>1) { posList.add(cur) }
        else if(cur == 0) { zeroCount++ }
        else if (cur == 1){ oneCount++ }
        else{ negList.add(cur) }
    }
    posList.sortByDescending { it }//양수 내림차순 sorting
    negList.sort()//음수 오름차순 sorting -3 -2 -1 ...

    if (posList.size.mod(2) == 1){ posList.add(1) }
    var sum = oneCount
    for (i in 0 until posList.size - 1 step 2){
        sum+=posList[i]*posList[i+1]
        //println("${posList[i]}*${posList[i+1]}")
    }

    if (negList.size.mod(2) == 1 && zeroCount>0){ negList.add(0) }//제일 마지막꺼 없애주는 효과
    if (negList.size.mod(2) == 1 && zeroCount==0){ negList.add(1) }
    for (i in 0 until negList.size - 1 step 2){
        sum+=negList[i]*negList[i+1]
    }

    println(sum)
}
