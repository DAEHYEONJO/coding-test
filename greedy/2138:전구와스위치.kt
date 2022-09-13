package greedy

import java.io.*

fun Char.toBoolean() = this == '1'
val offsetArray = intArrayOf(-1, 0, 1)

fun switchOnOff(index: Int, arr: BooleanArray) {
    offsetArray.forEach { offset ->
        arr[index + offset] = !arr[index + offset]
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val inputLightCaseOne =
        booleanArrayOf(true) + readLine().map { it.toBoolean() }.toBooleanArray() + booleanArrayOf(true)
    val inputLightCaseTwo = inputLightCaseOne.clone()
    val expectLight = booleanArrayOf(true) + readLine().map { it.toBoolean() }.toBooleanArray() + booleanArrayOf(true)

    // case: 첫번째 전구 킨 경우
    switchOnOff(1, inputLightCaseOne)
    var countFirst = 1
    var firstCaseComplete = false
    for (i in 2..n) {
        if (expectLight[i - 1] != inputLightCaseOne[i - 1]) {
            countFirst++
            switchOnOff(i, inputLightCaseOne)
        }
    }
    if (inputLightCaseOne[n] == expectLight[n]) firstCaseComplete = true

    // case: 첫번째 전구 안킨 경우
    var countSecond = 0
    var secondCaseComplete = false
    for (i in 2..n) {
        if (expectLight[i - 1] != inputLightCaseTwo[i - 1]) {
            countSecond++
            switchOnOff(i, inputLightCaseTwo)
        }
    }
    if (inputLightCaseTwo[n] == expectLight[n]) secondCaseComplete = true

    if (firstCaseComplete && !secondCaseComplete) {
        println(countFirst)
    } else if (!firstCaseComplete && secondCaseComplete) {
        println(countSecond)
    } else if (firstCaseComplete && secondCaseComplete) {
        println(minOf(countFirst, countSecond))
    } else {
        println(-1)
    }
}