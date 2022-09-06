package dp

val array = LongArray(100){i->0}

fun main() {
    println(fibo(50))
    println(fibonacci(50))
}

fun fibo(x : Int):Long{
    val dpForLoop = LongArray(51){0}.also {
        it[1] = 1
        it[2] = 1
    }
    for (i in 3..x){
        dpForLoop[i] = dpForLoop[i-1] + dpForLoop[i-2]
    }
    return dpForLoop[x]
}

fun fibonacci(x : Int) : Long{
    if ((x == 1) or (x == 2)) return 1L
    if (array[x]!=0L) return array[x]
    return (fibonacci(x-1) + fibonacci(x-2)).also {
        array[x] = it
    }
}


