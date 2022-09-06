package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val s = readLine().trim() // 5<=s.length<=2500
    // 한번 q를 만나게되면 u 를찾고 a -> c -> k 순으로 찾을것.
    // 연속으로 발견되는 quack sequence 는 한마리의 오리가 운것이다.
    // 한번 루프를 돌 때, 찾은 quack sequence 의 길이가 5배수가 아닌 경우 -1 출력.
    val check = BooleanArray(s.length){false}
    var cNum = 0
    val q = charArrayOf('q','u','a','c','k')
    var ans = 0

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        while(cNum != s.length){
            var quackIndex = 0
            var qCount = 0
            for (i in 0 until s.length) {
                if ((q[quackIndex]==s[i]) && !check[i]){
                    cNum++
                    qCount++
                    check[i] = true
                    quackIndex = (quackIndex + 1)%5
                }
            }
            if (qCount%5!=0 || qCount==0){
                w.write("-1")
                return@with
            }
            else{
                ans++
            }
        }
        w.write("${ans}")
        w.flush()
    }
}
