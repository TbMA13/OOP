package src

fun main() {
    var count = 0
    val test: Array3D<Int> = Array3D(2, 3, 4)
    for (i in 0..<2) {
        for (j in 0..<3) {
            for (k in 0..<4) {
                test.setValue(i, j, k, count)
                count++
            }
        }
    }
//    try {
//        val tempArray0 = test.getValues0(1)
//        println(test.getValues0(0))
//        test.setValues0(0, tempArray0)
//        println(test.getValues0(0))
//    }
//    catch (error: IndexOutOfBoundsException){
//        println("так делать нельзя, ибо: ${error.message}")
//    }
    println(test.getValues0(0))
    println(test.getValues0(1))

    println(test.getValues1(0))
    println(test.getValues2(0))
    println()
    println(test.getValues01(0,1))
    println(test.getValues02(0,1))
    println(test.getValues12(0,1))
    TODO("Демонстрация сеттеров")
//    val test2 = test.fill(12)
//    for (i in 0..<2) {
//        for (j in 0..<3) {
//            for (k in 0..<4) {
//                print("${test2.getValue(i, j, k)}, ")
//            }
//            println()
//        }
//    }
}