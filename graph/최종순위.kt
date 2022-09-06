package graph

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.out)).use{ writer->
        val n = readLine().toInt()
        repeat(n){
            val teamNum = readLine().toInt()
            val table = Array(teamNum){IntArray(teamNum){0}}
            val inDegreeList = Array(teamNum){0}
            val list = readLine().split(" ").map{it.toInt()}.toIntArray()
            for (i in 0 until list.size-1){
                for (j in i+1 until list.size){
                    table[list[i]-1][list[j]-1] = 1//list[i]->list[j] edge 표현
                    inDegreeList[list[j]-1]++
                }
            }
            val changeNum = readLine().toInt()
            repeat(changeNum){
                var(n1, n2) = readLine().split(" ").map{it.toInt()}
                n1--
                n2--
                if (table[n1][n2]==1){
                    table[n1][n2]=0
                    table[n2][n1]=1
                    inDegreeList[n2]--
                    inDegreeList[n1]++
                }
                else if(table[n2][n1]==1){
                    table[n2][n1]=0
                    table[n1][n2]=1
                    inDegreeList[n1]--
                    inDegreeList[n2]++
                }
            }
            //topology Sort 진행해주기
            val queue = LinkedList<Int>()
            val result = LinkedList<Int>()
            inDegreeList.forEachIndexed { index, i ->
                if (i==0){
                    queue.add(index)
                }
            }
            while (queue.isNotEmpty()) {
                val curRemove = queue.pollFirst()
                result.add(curRemove+1)
                table[curRemove].forEachIndexed { index, it ->
                    if (it == 1){
                        table[curRemove][index] = 0
                        inDegreeList[index]--
                        if (inDegreeList[index]==0) queue.add(index)
                    }
                }
            }
            if (result.size == teamNum){
                result.forEach { num ->
                    writer.write("$num ")
                }
                writer.write("\n")
            }else{
                writer.write("IMPOSSIBLE\n")
            }
            writer.flush()
        }
    }

}

