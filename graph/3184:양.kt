package graph

import java.io.*
import java.util.*

data class Position(val y: Int, val x: Int)
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (r, c) = readLine().split(" ").map{ it.toInt() }
    val dirs = arrayOf(intArrayOf(0,1), intArrayOf(1,0), intArrayOf(0,-1), intArrayOf(-1, 0))
    var yangCount = 0
    var wolfCount = 0

    val arr = Array(r){
        val row = readLine().toCharArray()
        row.forEach { c ->
            when(c){
                'o'->yangCount++
                'v'->wolfCount++
            }
        }
        row
    }
    val visited = Array(r){
        BooleanArray(c)
    }

    fun isValid(y: Int, x: Int): Boolean = (y in 0 until r) && (x in 0 until c) && (!visited[y][x]) && arr[y][x]!='#'


    fun bfs(y: Int, x: Int){
        val q = ArrayDeque<Position>()
        q.add(Position(y, x))
        var yCount = 0
        var wCount = 0
        when(arr[y][x]){
            'o'->yCount++
            'v'->wCount++
        }

        while(q.isNotEmpty()){
            val (curY, curX) = q.pollFirst()
            for((dy, dx) in dirs){
                val newY = curY + dy
                val newX = curX + dx
                if(isValid(newY, newX)){
                    q.add(Position(newY, newX))
                    visited[newY][newX] = true
                    when(arr[newY][newX]){
                        'o'->yCount++
                        'v'->wCount++
                    }
                }
            }
        }
        if (yCount > wCount) wolfCount-=wCount
        else yangCount-=yCount
    }

    for(y in 0 until r){
        for(x in 0 until c){
            if(isValid(y,x)){
                visited[y][x] = true
                bfs(y, x)
            }
        }
    }
    println("$yangCount $wolfCount")

}