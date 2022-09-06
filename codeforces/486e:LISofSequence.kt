package codeforces

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val a = intArrayOf(1) + readLine().trim().split(" ").map { it.toInt() }.toIntArray()
    val b = IntArray(n + 1)
    val aLisLength = IntArray(n + 1)
    val raLisLength = IntArray(n + 1)
    val temp = IntArray(n+1)
    val ans = IntArray(n+1){1}
    val leftMax = IntArray(n+1)
    leftMax[0] = -1
    val rightMin = IntArray(n+2)
    rightMin[n+1] = 100001
    //1: ai가 어느 LIS에도 포함되지 않음.
    //2: ai가 모든 LIS에는 아니지만 1개 이상은 포함되어 있는 경우
    //3: ai가 모든 LIS에 포함되어 있는 경우.

    //1,3,2,5
    //1,3,5
    //1,2,5

    fun lowerBound(arr: ArrayList<Int>, t: Int): Int {
        var s = 1
        var e = arr.size
        while (s < e) {
            val m = (s + e) / 2
            when {
                arr[m] < t -> s = m + 1
                arr[m] >= t -> e = m
            }
        }
        return s
    }

    fun lis(a: IntArray, lisLengthArray: IntArray): ArrayList<Int> {
        val lis = ArrayList<Int>()
        lis.add(-1)
        for (i in 1 until n + 1) {
            if (a[i] > lis.last()) {
                lis.add(a[i])
                lisLengthArray[i] = lis.size - 1
            } else {
                val index = lowerBound(lis, a[i])
                lis[index] = a[i]
                lisLengthArray[i] = index
            }
        }
        return lis
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        lis(a, aLisLength)
        //w.write(aLisLength.contentToString())
        //w.write("\n")

        for (i in 1 until n + 1) {
            b[n - i + 1] = 100000-a[i]
        }

        lis(b, raLisLength)
        for(i in 1 until n+1){
            temp[i] = raLisLength[i]
        }
        for (i in 1 until n+1){
            raLisLength[i] = temp[n-i+1]
        }
        //w.write(raLisLength.contentToString())
        //w.write("\n")
        val maxLisLength = aLisLength.maxOrNull()
        for(i in 1 until n+1){
            if(aLisLength[i] + raLisLength[i] == maxLisLength!!+1)
                ans[i] = 3 // LIS에 들어가는 ai 다 3으로 세팅.
        }
        //w.write(ans.contentToString())
        //w.write("\n")
        for(i in 1 until n+1){
            if (ans[i]==3){
                leftMax[i] = maxOf(leftMax[i-1], a[i])
            }else{
                leftMax[i] = leftMax[i-1]
            }
        }
        //w.write(leftMax.contentToString())
        //w.write("\n")
        for(i in n downTo 1){
            if (ans[i]==3){
                rightMin[i] = minOf(rightMin[i+1], a[i])
            }else{
                rightMin[i] = rightMin[i+1]
            }
        }
        //w.write(rightMin.contentToString())
        //w.write("\n")


        //모든 LIS에 들어가지 않는 ai 2로 바꿔야함.
        //내 왼쪽에있는 Lis중에 젤 큰게 나보다 작고,
        //내 오른쪽에 있는 Lis중에 젤 작은게 나보다 크면 3이다.

        //내 왼쪽에 있는 lis중에 젤 큰게 나보다 크거나 같거나,
        //또는 오른쪽 lis중에 젤 작은게 나보다 작거나 같으면 모든 Lis에 내가 포함되지 않았다는것.
        for(i in 1 until n+1){
            if (ans[i]==3 && (leftMax[i-1]>=a[i] || rightMin[i+1]<=a[i])){
                ans[i] = 2
            }
        }
        for(i in 1 until n+1){
            w.write("${ans[i]}")
        }
        w.flush()
    }





}