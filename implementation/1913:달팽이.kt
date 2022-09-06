package implementation

import java.io.*

fun main()   = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val nn = readLine().toInt()
    val a = Array(n){IntArray(n){0} }
    var y = 0
    var x = 0

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var count = n-1
        var num = n*n

        repeat(count){
            a[y++][x] = num--
        }

        while(count>0){
            repeat(count){
                a[y][x++] = num--
            }

            repeat(count){
                a[y--][x] = num--
            }

            count--

            repeat(count){
                a[y][x--] = num--
            }

            repeat(count){
                a[y++][x] = num--
            }

            count--

        }

        a[y][x] = num
        var ansY = 0
        var ansX = 0
        for(i in 0 until n){
            for(j in 0 until n){
                a[i][j].let{
                    if (it==nn){
                        ansY = i
                        ansX = j
                    }
                    w.write("$it ")
                }
            }
            w.write("\n")
        }
        w.write("${ansY+1} ${ansX+1}")
        w.flush()
    }
}