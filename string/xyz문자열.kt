package string

import java.io.*

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))){

   val pNum = readLine().toInt()
   val n = readLine().toInt()
    var strList = arrayOf("","X","YZ","ZX")
    var charCount = Array<Array<Long>>(3){Array<Long>(101){0} }.apply {
        this[0][1] = 1
        this[0][3] = 1
        this[1][2] = 1
        this[2][2] = 1
        this[2][3] = 1
    }//str 길이가 약 2^100 이라고 한다면, 각 자리숫자는 int max를 넘어선다.

    // X -> YZ
    // Y -> Z
    // Z -> X
    //1   X
    //2   YZ
    //3   ZX
    //4=1+2   XYZ
    //5=2+3   YZZX
    //6=3+4   ZXXYZ
    //7=4+5   XYZYZZX
    //8=5+6   YZZXZXXYZ
    //9=6+7   ZXXYZXYZYZZX
    //10=7+8  XYZYZZXYZZXZXXYZ
    //n-1번째 문자열은 (n-2)번째 문자열 + (n-3)번째 문자열
    for (i in 4 .. n){
        charCount[0][i] = charCount[0][i-2]+charCount[0][i-3]
        charCount[1][i] = charCount[1][i-2]+charCount[1][i-3]
        charCount[2][i] = charCount[2][i-2]+charCount[2][i-3]
    }

    fun getNthChar(index: Long, nth: Int): Char{
        if (nth<=3) return strList[nth][(index-1).toInt()]
        val leftCharCnt = charCount[0][nth-3]+charCount[1][nth-3]+charCount[2][nth-3]
        return if (index<=leftCharCnt){
            getNthChar(index, nth-3)
        }else{
            getNthChar(index-leftCharCnt, nth-2)
        }
    }

    when(pNum){
        1->{
            //n번째 단계 문자열의 길이 구하기
            println(charCount[0][n]+charCount[1][n]+charCount[2][n])
        }
        2->{
            val k = readLine().toLong()
            //n번째 단계 문자열의 k번째 char 출력하기
            println(getNthChar(k,n))
        }
        3->{
            //n번째 단계 문자열에서 char c의 개수
            val c = read().toChar()
            when(c){
                'X'-> println(charCount[0][n])
                'Y'-> println(charCount[1][n])
                'Z'-> println(charCount[2][n])
            }
        }
    }
}

