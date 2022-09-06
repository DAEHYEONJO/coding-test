package two_pointer

fun main() {
    val lim = 7
    val cd = intArrayOf(1,2,2,3,3,4,5)
    fun lowerBound(target: Int): Int{
        var s = 0
        var e = lim
        while(s<e){
            val m = (s+e)/2
            when{
                cd[m] < target -> s = m + 1
                cd[m] >= target -> e = m
            }
        }
        return s

    }

    fun upperBound(target: Int):Int{
        var s = 0
        var e = lim
        while(s<e){
            val m = (s+e)/2
            when{
                cd[m] <= target -> s = m + 1
                cd[m] > target -> e = m
            }
        }
        return s
    }
    println(lowerBound(-1))
    println(upperBound(-1))
    println(lowerBound(2))
    println(upperBound(2))
    println(lowerBound(4))
    println(upperBound(4))
    println(lowerBound(5))
    println(upperBound(5))

}