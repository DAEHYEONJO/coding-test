package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val t = readLine().toInt()
    repeat(t) {
        //n: 문서 개수, doc: 몇번째로 인쇄되었는지 궁금한 문서가 queue 에 몇번째에 있는지( 0 ~ n-1 )
        val (n, doc) = readLine().split(" ").map { it.toInt() }
        val priority = readLine().split(" ").map { it.toInt() }.toIntArray()
        val indexArray = IntArray(n){i->i}
        val zipArray = indexArray.zip(priority)

        //젤 앞의 문서 중요도보다 중요도가 높은 문서가 뒤에 존재하면 젤 앞의 문서를 뒤로 옮기기
        val queue = LinkedList<Pair<Int, Int>>()
        var ans = 0
        zipArray.forEach {
            queue.add(it)
        }

        while (true) {
            val first = queue.peekFirst()
            var flag = false
            queue.forEach {
                if (it.second > first.second){
                    flag = true
                    return@forEach
                }
            }
            if (flag){
                queue.add(queue.pollFirst())
            }else{
                ans++
                if (queue.pollFirst().first == doc) break
            }
        }
        println(ans)
    }
}
