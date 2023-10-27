package src

import org.junit.Test
import org.junit.Assert.*

class Array3DTest {
    private val testArray3D: Array3D<Int> = Array3D(x = 2, y = 3, z = 4)

    @Test
    fun getValue() {
        val expected: Int? = null
        assertEquals(expected, testArray3D.getValue(0,0,0))
    }


}