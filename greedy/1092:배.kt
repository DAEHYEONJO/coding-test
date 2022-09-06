package greedy

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val crane = readLine().trim().split(" ").map { it.toInt() }.toIntArray().sortedArrayDescending()
    val m = readLine().toInt()
    val boxes = readLine().trim().split(" ").map { it.toInt() }.sortedDescending().toMutableList()
    // crane 의 무게제한보다 무거운 박스는 옮길 수 없다.
    // 1분에 박스 하나씩 배에 실을 수 있음.
    // 모든 크레인은 동시에 움직임
    // crane 수 만큼 box 를 확인하면서 큰 무게의 박스부터 무거운 크레인에 먼저 넣어준다고 생각하자.
    var boxIndex = 0
    var craneIndex = 0
    var answer = 0

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        if (boxes.first() > crane.first()){
            w.write("-1")
            return@with
        }
        while (boxes.isNotEmpty()) {
            boxIndex = 0
            craneIndex = 0
            while (boxIndex<boxes.size && craneIndex<n){
                if (boxes[boxIndex]<=crane[craneIndex]){
                    boxes.removeAt(boxIndex)
                    craneIndex+=1
                }else{
                    boxIndex+=1
                }
            }
            answer += 1
        }
        w.write("$answer")
    }


}