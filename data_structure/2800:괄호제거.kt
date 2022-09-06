package data_structure

import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val expression = readLine().trim().toCharArray()
        val stack = Stack<Int>()
        val bracketPos = ArrayList<Pair<Int,Int>>()

        expression.forEachIndexed { index, c ->
            when(c){
                '('->stack.push(index)
                ')'->bracketPos.add(Pair(stack.pop(),index))
            }
        }

        val nums = IntArray(bracketPos.size){i->i}
        var r = 1
        val ans = HashSet<String>()
        fun combination(depth: Int, start: Int, arr: IntArray) {
            if(depth == r) {
                val temp = expression.clone()
                for (index in arr){
                    temp[bracketPos[index].first] = ' '
                    temp[bracketPos[index].second] = ' '
                }
                ans.add(temp.concatToString().replace(" ","").trim())
                return
            }
            for (i in start until bracketPos.size){
                arr[depth] = nums[i]
                combination(depth+1, i+1, arr.clone())
            }
        }
        // *hidden test case*
        // (((1+2)))(2/3)
        // 위와 같은 case 의 경우 [0,8] 의 괄호를 지우나 [1,7] 의 괄호를 지우나 동일한 문자열이 출력된다
        // 따라서 set 을 이용해서 문자열을 저장했다.

        for (i in 1 .. bracketPos.size){
            r = i
            combination(0,0, IntArray(r))
        }
        val sortedAns = ans.toArray().sortedBy { it.toString() }
        sortedAns.forEach {
            w.write("$it \n")
        }
        w.flush()
    }
}