package greedy

import java.io.*
import kotlin.math.pow

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val mk = readLine().trim()
        val num = StringBuilder()

        var start = 0
        // 최댓값은 5가 젤 앞에 등장하면 좋다, 즉, K 를 만날때 까지 기다린다.
        // 만약 K 를 만나지 않게 된다면 등장한 M 개수만큼 1 을 출력하고 K 를 만나게 되면 5*10^(M개수) 을 출력한다.
        var mCount = 0
        while (start<mk.length){
            if (mk[start++]=='M'){
                mCount+=1
                if (start == mk.length){
                    repeat(mCount){
                        num.append("1")
                    }
                }
            }else{
                num.append("5")
                repeat(mCount){
                    num.append("0")
                }
                mCount = 0
            }
        }
        w.write("${num.toString()}\n")

        // 최솟값은 10의 거듭제곱 형태로 나태내주면 젤 좋다. 왜냐하면 첫자리가 5가 아닌것이 가장 좋기 때문이다.
        // K 를 만나게 되면 그냥 바로 5 를 출력해준다. 왜냐하면 M..K 문자열은 첫자리가 5 로 변해버리기 때문이다.
        num.clear()
        start = 0
        mCount = 0
        while (start<mk.length){
            if (mk[start++]=='K') {
                repeat(mCount-1){
                    num.append("0")
                }
                num.append("5")
                mCount = 0
            }
            else{
                if (mCount == 0){
                    num.append("1")
                }
                mCount+=1
                if (start == mk.length){
                    repeat(mCount-1){
                        num.append("0")
                    }
                }

            }
        }
        w.write(num.toString())
        w.flush()

    }
}

//민겸 숫자는 0 이상의 정수 N에 대해 10N 또는 5 × 10N 꼴의 십진수를 대문자 M과 K로 이루어진 문자열로 표기한다.
// 10N 꼴의 십진수는 N + 1개의 M으로, 5 × 10N 꼴의 십진수는 N개의 M 뒤에 1개의 K를 이어붙인 문자열로 나타낸다. 즉, 아래 표처럼 나타낼 수 있다.

