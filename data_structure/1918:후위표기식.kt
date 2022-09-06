package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    //문자는 그냥 append
    //')' 가 나오면 '(' 가 나올때까지 다 pop 하고 연산자들 append
    //stack top 보다 우선순위가 높은 연산자는 그냥 push
    //stack top 보다 우선순위가 낮은 연산자라면 우선순위가 높은 연산자가 나올때까지 다 pop 하기
    //대신 ( 는 pop 하면 안됨.
    val line = readLine().trim()
    val stack = Stack<Char>()
    val result = StringBuilder()
    val opPriority = HashMap<Char,Int>(5).apply{
        put('+',0)
        put('-',0)
        put('*',1)
        put('/',1)
        put('(',2)
    }
    for (c in line){
        if(c.isLetter()){
            result.append(c)
        }else if(stack.isEmpty()){
            stack.push(c)
        }else{
            when (c) {
                ')' -> {
                    while(stack.isNotEmpty() && stack.peek().also{
                            if (it!='('){
                                result.append(it)
                            }
                            stack.pop()
                        }!='(');
                }
                '+','-','*','/','('->{
                    if (stack.peek()=='(') stack.push(c)
                    else{
                        val curPriority = opPriority[c]
                        if (opPriority[stack.peek()]!! < curPriority!!) stack.push(c)
                        else{
                            while (stack.isNotEmpty() && stack.peek()!='(' && opPriority[stack.peek()]!! >= curPriority){
                                result.append(stack.pop())
                            }
                            stack.push(c)
                        }
                    }
                }
            }
        }
    }
    while (stack.isNotEmpty()){
        result.append(stack.pop())
    }
    println(result.toString())
}