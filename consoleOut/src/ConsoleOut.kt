package src

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

enum class COLOR(val code: String) {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BLUE("\u001B[36m"),
    YELLOW("\u001B[34m"),
    VIOLET("\u001B[35m"),
    WHITE("\u001B[0m")
}

class ConsoleOut(targetText: String, textSize: Int = 1, var color: COLOR = COLOR.WHITE) {
    private val fontArraySize7 = mutableListOf<MutableList<String>>() // std::vector<std::vector<std::string>>
    private val fontArraySize5 = mutableListOf<MutableList<String>>()
    var size = 1
        set(value) {
            if (value != 1 && value != 5 && value != 7) {
                throw NoSuchElementException("Доступны только символы размером: 1, 5, 7")
            }
            field = value
        }
    var text = ""
        set(value) {
            for (currentSymbol in value) {
                if ((currentSymbol.uppercaseChar().code < 65 || currentSymbol.uppercaseChar().code > 90) && currentSymbol != ' ') {
                    throw NoSuchElementException("Доступны только символы латинского алфавита")
                }
            }
            field = value
        }

    init {
        size = textSize
        text = targetText
        fontSymbolsRead(5)
        fontSymbolsRead(7)
    }

    private fun symbolsSplit(array: MutableList<String>/*массив из строк файла*/, textSize: Int) {
        var startIndex = 0 // этот индекс в дальнейшем обновляется и подразумевает собой начало очередной буквы
        for (index in 0..<array[0].length) { // Проходимся по всем элементам первой строки
            if (array[0][index] == ' ') { // Если в первой строке находим пробел, то проверяем остальные строки на пробел в этом индексе
                var flag = true
                for (i in 0..<array.size) { // array.size - количество строк
                    if (array[i][index] == ' ') {
                        continue
                    } else {
                        flag = false // Нашли символ, отличный от пробела - прервали проверку символом этого индекса на пробелы
                        break
                    }
                }
                if (flag) { // если все-таки все символа одного индекса оказались пробелами...
                    val tempBigSymbol = mutableListOf<String>() // Создаем большой массив из строк - букву
                    for (i in 0..<array.size) { // for по всем строкам файла
                        var tempSubString = "" // этот String - строка буквы
                        for (j in startIndex..index) { // проходимся от startIndex, который мы каждую новую букву заменяем на
                            // первый индекс следующей буквы
                            tempSubString += array[i][j]
                        }
                        tempBigSymbol.add(tempSubString) // добавляем в букву (массив из строк) найденную подстроку
                    }
                    if (textSize == 7) { // В зависимости от размера текущего шрифта, добавляю в разные приватные переменные класса найденные буквы
                        fontArraySize7.add(tempBigSymbol) // fontArraySize7 состоит из массивов строк, то есть включает себя переменные tempBigSymbol
                    } else if (textSize == 5) {
                        fontArraySize5.add(tempBigSymbol)
                    }
                    startIndex = index + 1 // меняем startIndex на индекс первого символа следующей буквы
                } else {
                    continue
                }
            }
        }
    }

    private fun fontSymbolsRead(textSize: Int) {
        val file = File("./font/$textSize.txt")
        val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
        val tempArray: MutableList<String> = mutableListOf()
        reader.lines().forEach { tempArray.add(it) }
        // std::vector<std::string>
        symbolsSplit(tempArray, textSize)
    }

    override fun toString(): String {
        var resultString = color.code
        for (i in 0..<size) {
            for (index in text.indices) {
                val currentSymbolCode = text[index].uppercaseChar().code // код символа ASCII
                when (size) {
                    1 -> {
                        return color.code + text + COLOR.WHITE.code
                    }

                    5 -> {
                        if (text[index] == ' ') {
                            resultString += "   "
                            continue
                        } else if (currentSymbolCode < 65 || currentSymbolCode > 90) {
                            throw NoSuchElementException("Доступны только символы латинского алфавита")
                        } else {
                            resultString += fontArraySize5[currentSymbolCode - 65][i]
                        }
                    }

                    7 -> {
                        if (text[index] == ' ') {
                            resultString += "      "
                            continue
                        } else if (currentSymbolCode < 65 || currentSymbolCode > 90) {
                            throw NoSuchElementException("Доступны только символы латинского алфавита")
                        } else {
                            resultString += fontArraySize7[currentSymbolCode - 65][i]
                        }
                    }
                }
            }
            resultString += "\n${color.code}"
        }
        resultString += COLOR.WHITE.code
        return resultString
    }
}