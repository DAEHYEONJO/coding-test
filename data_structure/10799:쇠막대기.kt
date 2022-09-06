package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val line = readLine().trimEnd()
    var curLaserIndex = 0
    var curBarCount = 0
    val barStack = LinkedList<Int>()
    var result = 0

    //여는괄호 다음 닫는 괄호가 바로 나온다 -> 레이저 -> result += 현재 막대기 수
    //여는괄호 다음 여는 괄호가 바로 나온다 -> 막대기 시작점
    //닫는 괄호가 나왔을때 내 막대기가 잘린적이 있다면 한조각 더 나뉠 수 있으므로 result++ & curBarCount--
    //닫는 괄호가 나왔다면 내 막대기의 시작 위치 전에 레이저가 등장했는지 여부를 체크해야 한다
    //제일 최근에 등장했던 레이저의 index 번호를 기록 해 놓는다
    //닫힌 괈호 등장시 내 막대기의 여는 괄호 index 보다 최근 등장한 레이저 index 번호가 크다면 result++

    var lineIndex = 0
    while (lineIndex<line.length){
        when(line[lineIndex]){
            '('->{
                if (line[lineIndex+1] == ')') {
                    curLaserIndex = lineIndex
                    lineIndex+=1
                    result+=curBarCount
                }
                else{
                    barStack.add(lineIndex)
                    curBarCount++
                }
            }
            ')'->{
                val curBarStartIndex = barStack.pollLast()
                if (curBarStartIndex < curLaserIndex) result++
                curBarCount--
            }
        }
        lineIndex++
    }
    println(result)
}