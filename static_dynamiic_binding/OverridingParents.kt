package static_dynamiic_binding

open class OverridingParents {
    open fun a(){
        println("OverridingParents")
    }

    fun b(){
        println("OverridingParents b")
    }
}

class OverridingSub: OverridingParents() {
    override fun a() {
        println("OverridingSub")
    }

}

open class OverloadingParents{
    companion object{
        fun o(){
            println("OverloadingParents")
        }
    }
}

class OverloadingSub: OverloadingParents(){
    companion object{
        fun o(){
            println("OverloadingSub")
        }
    }

    fun afaf(){

    }
}

class Test {

    lateinit var name : String
    fun testNameInit(){
        if (this::name.isInitialized){
            // access name
        }else{
            // 초기화
        }
    }
}

object TestCompanionObject{
    const val a = 1
    const val b = 2
    var c = 3
    @JvmField
    val d = 4
}

class TestCO(var v: Int = 0){
    companion object{
        var b = 0
    }
    fun addB(){
        println(b++)
    }

}

class ClassName private constructor() {

    // 외부에서 static 형태로 접근 가능
    companion object {
        @JvmStatic
        private var instance : ClassName? = null
        @JvmStatic
        fun getInstance(): ClassName? {
            synchronized(ClassName::class.java){
                if (instance==null) instance = ClassName()
                return instance!!
            }
        }
    }
}


fun main() {
    val overridingSub: OverridingParents = OverridingSub()
    overridingSub.a()//OverridingSub
    overridingSub.b()//OverridingParents b

    val overridingParents: OverridingParents = OverridingParents()
    overridingParents.a()//OverridingParents

    val listWithNulls: List<String?> = listOf("A", null, "B")
    for (item in listWithNulls) {
        item?.let { println(it) } // prints A and ignores null
    }


    var temp: String? = "hihi"
    val t = temp!!
    println(t)

    println("주소값: $TestCompanionObject")
    println("주소값: $TestCompanionObject")
    println("주소값: $TestCompanionObject")
    println("주소값: $TestCompanionObject")
    println("주소값: $TestCompanionObject")
    println("TestCompanionObject a : ${TestCompanionObject.a}")
    println("TestCompanionObject c change before : ${TestCompanionObject.c}")
    TestCompanionObject.c = 4
    println("TestCompanionObject c change after : ${TestCompanionObject.c}")

    println(ClassName.getInstance())
    println(ClassName.getInstance())
    println(ClassName.getInstance())
    println(ClassName.getInstance())
    println(ClassName.getInstance())

    repeat(5){
        val testCO = TestCO()
        println("v: ${testCO.v}")
        testCO.addB()
    }

    val t1 = T(4)
    val t2 = T(5)

    if(t1 == t2) {
        println("true")
    }else{
        println("false")
    }

}

data class T(var a : Int)