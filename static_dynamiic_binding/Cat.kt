package static_dynamiic_binding

open class Animal {
    open fun testInstanceMethod() {
        println("The instance method in Animal")
    }

    companion object {
        fun testClassMethod() {
            println("The static method in Animal")
        }
    }
}

class Cat : Animal() {
    override fun testInstanceMethod() {
        println("The instance method in Cat")
    }

    companion object {
        fun testClassMethod() {
            println("The static method in Cat")
        }

    }
}

fun main() {
    val myCat = Cat()
    Cat.testClassMethod()//cat
    val myAnimal: Animal = myCat
    Animal.testClassMethod()//ani
    myAnimal.testInstanceMethod()//cat
}