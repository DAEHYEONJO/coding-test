package two_pointer

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val a = readLine().trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()
    var ans = 0L
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        //w.write("${a.contentToString()}\n")
        for(start in 0 until n-2){
            if (a[start]>0) break
            var second = start + 1
            var third = n-1
            while(second<third){
                val sum = a[start]+a[second]+a[third]
                when{
                    sum<0 -> second++ //합이 음수면 second index를 키워줘서 전체 합이 커지도록
                    sum>0 -> third-- //합이 양수면 third index를 작게해줘서 전체 합이 작아지도록
                    else -> { // 합이 0인 경우
                        if(a[second]==a[third]){ //second가 가리키는 애랑 third가 가리키는 애가 같으면 sorting된 배열이므로 mC2 더해주기
                            val m = (third-second+1).toLong()
                            ans+=(m*(m-1)/2)
                            break
                        }else{ // a[second] 보다 a[third] 가 큰 경우
                            var sCount = 0L
                            var sTmp = second
                            var tCount = 0L
                            var tTmp = third
                            while(sTmp<third && a[second]==a[sTmp]){
                                sCount+=1L
                                sTmp+=1
                            }

                            while(tTmp>second && a[third]==a[tTmp]){
                                tCount+=1L
                                tTmp-=1
                            }
                            second = sTmp
                            third = tTmp
                            ans+=(tCount*sCount)
                        }
                    }
                }
            }
        }
        w.write("$ans")
        w.flush()
    }
}