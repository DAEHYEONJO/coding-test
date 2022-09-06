package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){

    //(()[[]])
    //2*(2 + 3*3)
    //2*2 + 2*3*3
    //여는 괄호 등장시 stack.push() & multiply*=2{'('} or 3{'['}
    //닫는 괄호 등장시 stack.top()과 str[index-1] 모두 짝이 맞다면 answer+=multiply
    //& multiply/=2 or 3
    //닫는 괄호 등장시 stack is empty or top 과 짝이 맞지 않으면 return 0
    val str = readLine().trimEnd()
    val stack = Stack<Char>()

    var multiply = 1
    var answer = 0

    fun getAnswer(): Int {
        str.forEachIndexed{ index, it ->
            when(it){
                '('->{
                    stack.push(it)
                    multiply*=2
                }
                ')'->{
                    if (stack.isEmpty()||stack.peek() == '[' ) {
                        return 0
                    }else{
                        if (str[index-1]=='(') answer+=multiply
                        multiply/=2
                        stack.pop()
                    }
                }
                '['->{
                    stack.push(it)
                    multiply*=3
                }
                ']'->{
                    if (stack.isEmpty()||stack.peek() == '(' ){
                        return 0
                    }else{
                        if (str[index-1]=='[') answer+=multiply
                        multiply/=3
                        stack.pop()
                    }
                }
            }
        }
        if (stack.isNotEmpty()) return 0
        return answer
    }
    println(getAnswer())
}