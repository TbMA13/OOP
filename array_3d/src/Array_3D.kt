package src

class Array3D<type>(private val x: Int, private val y: Int, private val z: Int) {
    private val mainList = mutableListOf<type?>()
    private var size = x * y * z

    /**  Выполняется при инициализации класса*/
    init {
        require(x > 0 && y > 0 && z > 0) { "Размерность массивов должна быть больше нуля" }
        repeat (size) {
            mainList.add(null)
        }
    }

    /**К сожалению, в Kotlin отсутствует ключевое слово static.
     *Замена на companion object может сделать подобие static, но логика будет иной (необходимо передавать аргументы типа, объекта класса),
     *поэтому метод сделан не статичным*/
    fun fill(value: type?): Array3D<type?> {
        val tempArray3D = Array3D<type?>(x, y, z)
        for (index in mainList.indices) {
            tempArray3D.mainList[index] = value
        }
        return tempArray3D
    }

    fun print(){
        print("{")
        for (i in 0..<x){
            print(this.getValues0(i))
            if (i != x - 1){
                print(", ")
            }
        }
        println("}\n")
    }

    /**Геттеры:*/
    fun getValue(i: Int, j: Int, k: Int): type? {
        if (i >= x || j >= y || k >= z || i < 0 || j < 0 || k < 0) {
            throw IndexOutOfBoundsException("Индексы должны быть в пределах массива")
        }
        return mainList[(mainList.size / x) * i + (mainList.size / (x * y)) * j + k]
    }

    private fun getValuesX(x1: Int, arrayIndex: Int): MutableList<MutableList<type?>> {
        val returnList = mutableListOf<MutableList<type?>>()
        var temp = mutableListOf<type?>()
        when (arrayIndex) { // when тут - как switch()
            0 -> {
                for (j in 0..<y) {
                    for (k in 0..<z) {
                        temp.add(this.getValue(x1, j, k))
                    }
                    returnList.add(temp)
                    temp = mutableListOf()
                }
            }

            1 -> {
                for (i in 0..<x) {
                    for (k in 0..<z) {
                        temp.add(this.getValue(i, x1, k))
                    }
                    returnList.add(temp)
                    temp = mutableListOf()
                }
            }

            2 -> {
                for (i in 0..<x) {
                    for (j in 0..<y) {
                        temp.add(this.getValue(i, j, x1))
                    }
                    returnList.add(temp)
                    temp = mutableListOf()
                }
            }
        }

        return returnList
    }

    fun getValues0(i: Int): MutableList<MutableList<type?>> {
        return getValuesX(i, 0)
    }

    fun getValues1(j: Int): MutableList<MutableList<type?>> {
        return getValuesX(j, 1)
    }

    fun getValues2(k: Int): MutableList<MutableList<type?>> {
        return getValuesX(k, 2)
    }

    private fun getValuesXX(x1: Int, x2: Int, arrayIndex: Int): MutableList<type?> {

        val returnList = mutableListOf<type?>()
        when (arrayIndex) {
            1 -> {
                for (k in 0..<z) {
                    returnList.add(getValue(x1, x2, k))
                }
            }

            2 -> {
                for (j in 0..<y) {
                    returnList.add(getValue(x1, j, x2))
                }
            }

            12 -> {
                for (i in 0..<x) {
                    returnList.add(getValue(i, x1, x2))
                }
            }
        }
        return returnList
    }

    fun getValues01(i: Int, j: Int): MutableList<type?> {
        return getValuesXX(i, j, 1)
    }

    fun getValues02(i: Int, k: Int): MutableList<type?> {
        return getValuesXX(i, k, 2)
    }

    fun getValues12(j: Int, k: Int): MutableList<type?> {
        return getValuesXX(j, k, 12)
    }

    /** Сеттеры:*/
    fun setValue(i: Int, j: Int, k: Int, item: type?) {
        if (i >= x || j >= y || k >= z || i < 0 || j < 0 || k < 0) {
            throw IndexOutOfBoundsException()
        }
        mainList[(mainList.size / x) * i + (mainList.size / (x * y)) * j + k] = item

    }

    private fun setValuesX(x1: Int, arrayIndex: Int, newValues: MutableList<MutableList<type?>>) {
        when (arrayIndex) {
            0 -> {
                for (j in 0..<y) {
                    for (k in 0..<z) {
                        if (j < newValues.size && k < newValues[j].size) {
                            setValue(x1, j, k, newValues[j][k])
                        }
                    }
                }
            }

            1 -> {
                for (i in 0..<x) {
                    for (k in 0..<z) {
                        if (i < newValues.size && k < newValues[i].size) {
                            setValue(i, x1, k, newValues[i][k])
                        }
                    }
                }
            }

            2 -> {
                for (i in 0..<x) {
                    for (j in 0..<y) {
                        if (i < newValues.size && j < newValues[i].size) {
                            setValue(i, j, x1, newValues[i][j])
                        }
                    }
                }
            }

        }
    }

    fun setValues0(i: Int, newValues: MutableList<MutableList<type?>>) {
        setValuesX(i, 0, newValues)
    }

    fun setValues1(j: Int, newValues: MutableList<MutableList<type?>>) {
        setValuesX(j, 1, newValues)
    }

    fun setValues2(k: Int, newValues: MutableList<MutableList<type?>>) {
        setValuesX(k, 2, newValues)
    }

    private fun setValuesXX(x1: Int, x2: Int, arrayIndex: Int, newValues: MutableList<type?>) {
        when (arrayIndex) {
            1 -> {
                for (k in 0..<z) {
                    setValue(x1, x2, k, newValues[k])
                }
            }

            2 -> {
                for (j in 0..<y) {
                    setValue(x1, j, x2, newValues[j])
                }
            }

            12 -> {
                for (i in 0..<x){
                    setValue(i, x1, x2, newValues[i])
                }
            }
        }
    }
    fun setValues01(i: Int, j: Int, newValues: MutableList<type?>){
        setValuesXX(i, j, 1, newValues)
    }
    fun setValues02(i: Int, k: Int, newValues: MutableList<type?>){
        setValuesXX(i, k, 2, newValues)
    }
    fun setValues12(j: Int, k: Int, newValues: MutableList<type?>){
        setValuesXX(j, k, 12, newValues)
    }
    
}