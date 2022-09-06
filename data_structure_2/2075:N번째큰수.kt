package data_structure_2

import java.util.*
import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val ba1 = "101"
    val ba2 = "102"
    println(ba1.last().digitToInt())
    println(ba1.toByte())
    println(ba1.toByte() - "101".toByte())
    println(ba2.toByte() - "101".toByte())
    //목표는 n^2개의 서로 다른 숫자 중 n 번째로 큰 수를 찾는 것이다.
    //priorityQueue 에 저장되는 element 수를 n개로 제한한다.
    //초기에 n 개의 입력에 대하여 priorityQueue 를 채워 넣는다.
    //여기서 priorityQueue 는 min heap 이다.
    //추가적으로 들어오는 (n-1)*n 개의 입력에 대하여 priorityQueue 의 root 값보다 큰 값이 들어온다면
    //root 값을 pop 하고 더 큰 값을 queue 에 넣는다.
    //끝까지 반복하게 되면 priorityQueue 는 n개의 원소만 저장 되므로 N^2 개의 숫자에 대하여
    //n 번째로 큰 수는 root 에 위치하게 된다.
    // 1 ~ 100 까지 입력을 생각해보자.
    // n = 10 이라면 초기 queue 엔 1~10이 담길 것이다.
    // root == 1 이고, 추후에 들어오는 입력은 11 ~ 20 이다.
    // 위의 과정을 반복하게 되면 마지막에는 91 ~ 100 이 queue 에 담겼을 것이다.
    // 따라서 priorityQueue.poll() 을 진행하게 되면 Root 값인 91 ( 1 ~ 100 중 10 번째로 큰 수 ) 가 출력된다.
    // 메모리 초과를 방지하기 위하여 queue 에 n 개만 저장하는 것이다.
    // n^2 개를 저장하고 n-1 번 poll() 을 진행해도 통과는 하지만 문제의 의도가 아닌것 같다.
    val n = readLine().toInt()
    val pq = PriorityQueue<Int>(n)
    readLine().split(" ").map{it.toInt()}.forEach {
        pq.add(it)
    }
    repeat(n-1){
        readLine().split(" ").map{it.toInt()}.forEach{
            if (pq.peek() < it){
                pq.poll()
                pq.add(it)
            }
        }
    }
    val list = ArrayList<HashMap<String, Objects>>()



}