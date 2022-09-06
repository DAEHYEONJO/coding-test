package greedy

class Test {




}

class AA(var num : Int, var age : Int){
    override fun toString(): String {
        return "num : $num age : $age"
    }
}

fun main() {
    val aa = AA(10, 20)
    val bb = aa.apply {
        this.num = 2
    }

    println(aa)
    println(bb)

    bb.num = 3

    println(aa)
    println(bb)

}