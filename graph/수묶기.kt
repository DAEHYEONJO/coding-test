package graph

import java.io.*

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))){
    // 양수 와 음수 나누기
    // 양수에서 1 제외한것 count 하기 -> 짝수개면 큰것부터 차례대로 2개씩 곱해주기
    // 0 존재 유무 판단하기
    // 음수 count 하기 -> 작은것부터 차례대로 2개씩 곱해주기
    val n = readLine().toInt()
    var list = Array<Int>(n){0}

    repeat(n){ list[it] = readLine().toInt() }
    list = list.sorted().reversed().toTypedArray()

    val posList = list.filter { it > 0 }
    val posSize = posList.size
    val startOne = posList.indexOfFirst { it == 1 }
    var posSum = if (startOne!=-1){
        //1이 존재한다는 의미
        //1전까지 큰거부터 2개씩 곱해서 더해주기 & 1개수만큼 더해주기
        getSum(posList, 0, startOne, 2) + (posSize - startOne)

    }else{
        //1이 존재하지 않음
        //2개씩 큰거부터 짝지어서 곱해서 더해주기
        getSum(posList, 0, posSize, 2)
    }

    var negList = list.slice(posSize until n)
    val negSize = negList.size
    println(negList)
    var negSum = 0
    if (negList[0]!=0) {
        // 0 이 존재하지 않는다 -> negList 의 갯수가 홀수인지 짝수인지 판단하여 sum 구해주기
        if (negSize.mod(2)==0){
            for (i in 0 until negSize-1 step 2){
                negSum+=negList[i]*negList[i+1]
            }
        }else{

        }
    }else{
        val lastZero = negList.indexOfLast { it == 0 }

    }
}

fun getSum(list : List<Int>, startIndex:Int, lastIndex : Int, step:Int): Int{
    var sum = 0
    println("lastIndex : $lastIndex")
    if (lastIndex.mod(2) == 0){//짝수개 존재함
        for (i in startIndex until lastIndex step step){
            sum += list[i]*list[i+1]
            println("i : $i ${list[i]}*${list[i+1]}")
        }
    }else{//홀수개 존재함
        for (i in startIndex until lastIndex - 1 step step){
            sum += list[i]*list[i+1]
            println("i : $i ${list[i]}*${list[i+1]}")
        }
        sum+=list[lastIndex-1]
        println("${list[lastIndex-1]} 또 더해줌")
    }
    println("posSum : $sum")
    return sum
}