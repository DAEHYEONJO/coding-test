package dp

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    //longest increasing subsequence 문제다.
    //즉 서있는 줄에서 증가하는 가장 긴 부분 수열을 찾는다면, 해당 인원들은 배치를 바꿔줄 필요가 없다.
    //해당 애들을 제외한 다른 애들을 순서대로 끼워 맞추는 것은 1회 시도에 가능하다
    //따라서 답은 n - LIS.Length 다.
    val n = readLine().toInt()
    val nums = IntArray(n) { readLine().toInt() }
    val increasingArr = ArrayList<Int>(n)
    increasingArr.add(nums.first())

    fun binarySearch(target: Int): Int{
        var start = 0
        var end = increasingArr.size
        while(start<=end){
            val mid = (start + end).div(2)
            when{
                increasingArr[mid] == target -> return mid
                increasingArr[mid] < target -> start = mid + 1
                increasingArr[mid] > target -> end = mid - 1
            }
        }
        return start
    }

    for(i in 1 until n){
        if (increasingArr.last() < nums[i]) increasingArr.add(nums[i])
        else increasingArr[binarySearch(nums[i])] = nums[i]
    }

    println(n-increasingArr.size)

}