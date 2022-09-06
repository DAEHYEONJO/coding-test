package string

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val word = readLine().trim()

    var answer = 0L
    val moSet = setOf('A', 'E', 'I', 'O', 'U')
    word.map {
        if (it=='_') '_' else it.uppercaseChar()
    }

    fun dfs(index: Int, moCount: Int, jaCount: Int, lExist: Boolean, tempAnswer: Long) {
        if (moCount == 3 || jaCount == 3) {
            return
        }
        if (index == word.length) {
            if (lExist) answer += tempAnswer
            return
        }


        if (word[index] == '_'){
            // 밑줄에 자음 넣는 경우(L 제외)
            dfs(index+1, 0, jaCount+1, lExist, tempAnswer*20)
            // 밑줄에 L 넣는 경우
            dfs(index+1, 0, jaCount+1, true, tempAnswer)
            // 밑줄에 모음 넣는 경우
            dfs(index+1, moCount+1, 0, lExist, tempAnswer*5)

        }else if (word[index] in moSet){ // 현재 index 문자가 모음인 경우
            dfs(index+1, moCount+1, 0, lExist, tempAnswer)
        }else{ // 현재 index 문자가 자음인 경우
            val curCharIsL = if (!lExist) word[index]=='L' else lExist
            dfs(index+1, 0, jaCount+1, curCharIsL, tempAnswer)
        }
    }

    dfs(0, 0, 0, false, 1L)

    println(answer)

}