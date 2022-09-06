package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val n = readLine().toInt()
    val nn = readLine().toInt()
    val a = Array(n) { IntArray(n) { 0 } }
    var y = -1
    var x = 0
    //dir: 하, 우, 상, 좌
    val dirs = arrayOf(arrayOf(1, 0), arrayOf(0, 1), arrayOf(-1, 0), arrayOf(0, -1))
    var num = n*n
    val range = (0 until n)



    var dirIndex = 0
    var ansY = 0
    var ansX = 0

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        fun isValidCoordinate(y: Int, x: Int): Boolean = (y in range) && (x in range)
        while(num > 0){
            val newY = y + dirs[dirIndex][0]
            val newX = x + dirs[dirIndex][1]

            if (!isValidCoordinate(newY, newX) || a[newY][newX]!=0){

                dirIndex = (dirIndex+1)%4
                continue
            }

            y = newY
            x = newX

            if (num == nn){
                ansY = y+1
                ansX = x+1
            }

            a[y][x] = num--
        }

        repeat(n){ y ->
            repeat(n){ x ->
                w.write("${a[y][x]} ")
            }
            w.write("\n")
        }
        w.write("$ansY $ansX")
        w.flush()
    }


}