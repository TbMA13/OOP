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

class ConsoleOut(targetText: String, textSize: Int, var color: COLOR) {
    private val fontArraySize7 = mutableListOf<MutableList<String>>()
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

    private fun symbolsSplit(array: MutableList<String>, textSize: Int) {
        var startIndex = 0
        var count = 0
        for (index in 0..<array[0].length) {
            if (array[0][index] == ' ') {
                var flag = true
                for (i in 0..<array.size) {
                    if (array[i][index] == ' ') {
                        continue
                    } else {
                        flag = false
                        break
                    }
                }
                if (flag) {
                    val tempBigSymbol = mutableListOf<String>()
                    for (i in 0..<array.size) {
                        var tempSubString = ""
                        for (j in startIndex..index) {
                            tempSubString += array[i][j]
                        }
                        tempBigSymbol.add(tempSubString)
                    }
                    if (textSize == 7) {
                        fontArraySize7.add(tempBigSymbol)
                    } else if (textSize == 5) {
                        fontArraySize5.add(tempBigSymbol)
                    }
                    startIndex = index + 1
                    count++
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