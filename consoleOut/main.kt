package src
fun main() {
    val test = ConsoleOut("hello world", 7, COLOR.RED)
    try {
        println(test)
        test.size = 5
        test.color = COLOR.VIOLET
        println(test)
        test.text = "green Text"
        test.size = 1
        test.color = COLOR.GREEN
        println(test)
    }
    catch (error:NoSuchElementException){
        println(error.message)
    }
}