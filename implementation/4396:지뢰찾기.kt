package implementation

import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val origin = Array(n){CharArray(n)}
    val dir = arrayOf(arrayOf(-1,0), arrayOf(-1, 1), arrayOf(0, 1),
    arrayOf(1, 1), arrayOf(1, 0), arrayOf(1, -1), arrayOf(0, -1), arrayOf(-1, -1))
    val range = (0 until n)
    val a = Array(n){IntArray(n){0} }
    repeat(n){ y ->
        val line = readLine().trim().toCharArray()
        origin[y]= line
        line.forEachIndexed{ x, c ->
            if(c=='*'){
                for((dy, dx) in dir){
                    val newY = y + dy
                    val newX = x + dx
                    if (newY in range && newX in range){
                        a[newY][newX]+=1
                    }
                }
            }
        }
    }

    BufferedWriter(OutputStreamWriter(System.`out`)).use { w ->
        val out = Array(n){
            readLine().trim().toCharArray()
        }
        var open = false
        repeat(n){ y->
            out[y].forEachIndexed{x, c ->
                if (c=='x'&&origin[y][x]=='*'){
                    open = true
                    return@repeat
                }
            }
        }
        repeat(n){ y ->
            out[y].forEachIndexed{x ,c ->
                if (open && origin[y][x]=='*') w.write("*")
                else{
                    when(c){
                        'x'->{
                            w.write("${a[y][x]}")
                        }
                        '.'->{
                            w.write(".")
                        }
                    }
                }
            }
            w.write("\n")
        }

        w.flush()
    }
}