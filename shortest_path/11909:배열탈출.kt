package shortest_path

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    // 1<= i, j <n 인경우, 오른쪽 아래쪽 으로만 감
    // i == n (젤 밑줄) 인 경우 오른쪽으로만 감
    // j == n (젤 오른쪽줄) 인 경우 아랫쪽으로만 감
    val dirs1 = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0)) // 오른쪽, 아래쪽
    val dirs2 = arrayOf(intArrayOf(0, 1)) // 오른쪽
    val dirs3 = arrayOf(intArrayOf(1, 0)) // 아래쪽
    val arr = Array(n + 1) { IntArray(n + 1) }
    val costArr = Array(n + 1) { IntArray(n + 1) { Int.MAX_VALUE } }

    // (1,1)을 시작지점으로 하기 위해서 IntArray(1) 추가
    repeat(n) { row ->
        arr[row + 1] = IntArray(1) + readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    // 시작지점 cost 0으로 초기화
    costArr[1][1] = 0
    for (row in 1 until n+1){
        for (col in 1 until n+1){
            // row, col 값에 따라 상이한 dirs 배열 초기화
            val dirs: Array<IntArray> = when{
                row == n -> dirs2
                col == n -> dirs3
                else -> dirs1
            }
            val curArrPay = arr[row][col]
            val curCost = costArr[row][col]
            for ((dy, dx) in dirs){
                val nextY = row + dy
                val nextX = col + dx
                // 좌표 검사
                if (nextY !in (1 .. n) || nextX !in (1 .. n)) continue
                val nextArrPay = arr[nextY][nextX]
                // 추가적인 cost 여부 검사
                val diff = if (curArrPay > nextArrPay){
                    0
                }else{
                    nextArrPay - curArrPay + 1
                }
                val nextCost = curCost + diff
                // 내가 가려는 좌표가 다른곳에서 더 좋은 cost에 갈 수 있다면 가지 않기
                if (costArr[nextY][nextX] > nextCost){
                    costArr[nextY][nextX] = nextCost
                }
            }
        }
    }
    println(costArr[n][n])
}
