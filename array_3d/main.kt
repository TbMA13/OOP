package src
fun arrayFill(currentArray3D: Array3D<Int>){
    var count = 0
    val x = 2
    val y = 3
    val z = 4
    for (i in 0..<x) {
        for (j in 0..<y) {
            for (k in 0..<z) {
                currentArray3D.setValue(i, j, k, count)
                count++
            }
        }
    }
}
fun main() {
    val x = 2
    val y = 3
    val z = 4
    val test: Array3D<Int> = Array3D(x, y, z)
    arrayFill(test)

//    try {
//        val tempArray0 = test.getValues0(1)
//        println(test.getValues0(0))
//        test.setValues0(0, tempArray0)
//        println(test.getValues0(0))
//    }
//    catch (error: IndexOutOfBoundsException){
//        println("так делать нельзя, ибо: ${error.message}")
//    }
    println("Исходный массив:")
    test.print()
    val i = 0
    println("Демонстрация геттеров:")
    print("getValue0($i): ")
    println(test.getValues0(i))
    print("getValue1($i): ")
    println(test.getValues1(i))
    print("getValue2($i): ")
    println(test.getValues2(i))
    println()
    print("getValue01($i, $i): ")
    println(test.getValues01(i, i))
    print("getValue02($i, $i): ")
    println(test.getValues02(i, i))
    print("getValue12($i, $i): ")
    println(test.getValues12(i, i))
    println()

    println("\nДемонстрация сеттеров с заменой на \"9\":")
    val j = 1

    print("getValues0($i): ")
    println(test.getValues0(i))
    print("setValues0($i): ")
    test.setValues0(i, test.fill(9).getValues0(i))
    println(test.getValues0(i))
    println()

    arrayFill(test)
    print("getValues1($i): ")
    println(test.getValues1(i))
    print("setValues1($i): ")
    test.setValues1(i, test.fill(9).getValues1(i))
    println(test.getValues1(i))
    println()

    arrayFill(test)
    print("getValues2($i): ")
    println(test.getValues2(i))
    print("setValues2($i): ")
    test.setValues2(i, test.fill(9).getValues2(i))
    println(test.getValues2(i))
    println()

    arrayFill(test)
    print("getValues01($i, $j): ")
    println(test.getValues01(i, j))
    print("setValues01($i, $j): ")
    test.setValues01(i, j,  test.fill(9).getValues01(i, j))
    println(test.getValues01(i, j))
    println()

    arrayFill(test)
    print("getValues02($i, $j): ")
    println(test.getValues02(i, j))
    print("setValues02($i, $j): ")
    test.setValues02(i, j,  test.fill(9).getValues02(i, j))
    println(test.getValues02(i, j))
    println()

    arrayFill(test)
    print("getValues12($i, $j): ")
    println(test.getValues12(i, j))
    print("setValues12($i, $j): ")
    test.setValues12(i, j,  test.fill(9).getValues12(i, j))
    println(test.getValues12(i, j))
    println()
}