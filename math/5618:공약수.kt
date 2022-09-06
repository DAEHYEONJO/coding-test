import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    BufferedWriter(OutputStreamWriter(System.`out`)).use{ w ->
        val n = readLine().toInt()
        val nums = readLine().split(" ").map{it.toInt()}
        val gcd = GCD(nums[0],GCD(nums[1],nums.last()))
        var i = 1
        val set = HashSet<Int>()
        while( i*i <= gcd){
            if (gcd.mod(i) == 0){
                set.add(i)
                set.add(gcd/i)
            }
            i++
        }
        set.add(gcd)
        w.write("${set.sorted().joinToString(separator = "\n")}")
        w.flush()
    }

}

tailrec fun GCD(a: Int, b: Int) : Int{
    val remain = a.mod(b)
    if (remain==0) return b
    return GCD(b, remain)
}