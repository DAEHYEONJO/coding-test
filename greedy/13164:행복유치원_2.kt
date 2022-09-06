package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val(n, k) = readLine().split(" ").map{ it.toInt() }
    val h = readLine().split(" ").map{ it.toInt() }.toIntArray()//입력자체가 오름차순 sorting 됨
    val diff = IntArray(n-1){ i ->
        h[i+1]-h[i]
    }
    diff.sortDescending()//내림차순 소팅
    var total = h.last() - h.first()
    //2그룹으로 나눈다면 diff에서 젤 큰거 1개 빼주기 1346과 10그룹으로 나누기 (6과 10의 차이 = 4)->젤큼
    //3그룹으로 나눈다면 diff에서 젤 큰거 2개 빼주기 13, 56, 10
    //4그룹으로 나눈다면 diff에서 젤 큰거 3개 빼주기
    //빼준다는것은 양옆의 애들을 다른 그룹으로 본다는의미와 동일함
    repeat(k-1){
        total-=diff[it]
    }
    println(total)
}