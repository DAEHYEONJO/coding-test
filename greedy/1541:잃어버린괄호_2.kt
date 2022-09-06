package greedy

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val exp = readLine().trim()
    var curOp = '+'
    var sum = 0
    val sb = StringBuilder()

    fun calc(num: Int, op: Char){
        when(op){
            '+'->sum+=num
            '-'->sum-=num
        }
        sb.clear()
    }


    exp.forEach{
        when{
            it.isDigit() -> {
                sb.append(it)
            }
            else -> {
                calc(sb.toString().toInt(), curOp)
                if(curOp == '+'){
                    if (it=='-') curOp = it
                }
            }
        }
    }
    calc(sb.toString().toInt(), curOp)
    println(sum)


}


