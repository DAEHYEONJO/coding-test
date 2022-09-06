package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    var nn = 4 * n - 3
    val a = Array(nn) { CharArray(nn) { ' ' } }
    var y = 0
    var x = 0
    var nnn = 2*n-1
    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        var cc = 1
        nn-=1
        while(nnn>0){
            val c = when(cc%2){
                1->'*'
                else -> ' '
            }

            repeat(nn){
                a[y][x++] = c
            }
            repeat(nn){
                a[y++][x] = c
            }
            repeat(nn){
                a[y][x--] = c
            }
            repeat(nn){
                a[y--][x] = c
            }
            nnn-=1
            nn-=2
            cc++
            y+=1
            x+=1
        }
        a[y-1][x-1] = '*'
        for(i in 0 until 4*n-3){
            for(j in 0 until 4*n-3){
                w.write("${a[i][j]}")
            }
            w.write("\n")
        }
        w.flush()

    }
}