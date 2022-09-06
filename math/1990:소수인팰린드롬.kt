package math

import java.io.*
import kotlin.math.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val(a, b) = readLine().trim().split(" ").map{ it.toInt() }
        val isPrime = BooleanArray(b+1){true}
        isPrime[1] = false

        for(i in 2 until sqrt(b.toDouble()).toInt()+1){
            if(isPrime[i]){
                for(j in i+i until b+1 step i){
                    isPrime[j] = false
                }
            }
        }

        for (i in a .. b){
            if (isPrime[i] && isPalindrome(i.toString())){
                w.write("$i\n")
            }
        }
        w.write("-1")
        w.flush()
    }
    this.close()


}

fun isPalindrome(x: String): Boolean{
    for (i in 0 until x.length/2){
        if (x[i]!=x[x.length-1-i]) return false
    }
    return true
}