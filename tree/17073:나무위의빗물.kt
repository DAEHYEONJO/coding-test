package tree

import java.io.*
import java.util.*
//더이상 물이 움직이지 않을때는 루트의 물의 leaf로 다 이동한 상황이다.
//특정 node의 child로 물이 동일한 확률로 분배가 된다.
//결국 Leaf node들은 공평하게 물을 나눠갖게 된다.
//따라서 leaf node들은 각 각 평균적으로 w/leaf node 만큼의 물을 갖게 되고
//leaf node가 갖는 물의 평균의 평균은 (w/lefa node수)*(leaf node 수)/(lefa node 수) = (w)/(leaf node 수) 와 같다
//다르게 생각하면 표본의 평균은은 모집단의 평균과 동일하다.
//이 문제의 핵심은 주어진 tree에서 leaf node가 몇개인지 구하는 것이다.
//tree에서 leaf node는 degree = 1 인 특성을 지니고 있다.
//만약 root node가 degree = 1 일 수 있으므로, degrees 배열에서
//index = 0 을 제외한 다른 방에서 degree 값이 1 인 애들을 count 해주자.
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, w) = readLine().split(" ").map{ it.toInt() }
    val degrees = IntArray(n){0}
    repeat(n-1) { i ->
        val (n1, n2) = readLine().split(" ").map { it.toInt() }
        degrees[n1-1]++
        degrees[n2-1]++
    }
    var leafCount = 0
    for(i in 1 until n){
        if (degrees[i]==1) leafCount++
    }
    println(w.toDouble()/leafCount)
}