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
    val tempArray0 = test.getValues0(0)
    val tempArray1 = test.getValues1(0)
    val tempArray2 = test.getValues2(0)
    val tempArray01 = test.getValues01(0, 1)
    val tempArray02 = test.getValues02(0, 2)
    val tempArray12 = test.getValues12(1, 2)

    val test2 = test.fill(12)
    for (i in 0..<2) {
        for (j in 0..<3) {
            for (k in 0..<4) {
                print("${test2.getValue(i, j, k)}, ")
            }
            println()
        }
    }
}