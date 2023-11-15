package src.main

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

const val CONSOLE_SIZE = 91

interface Command {
    private fun volumeAndBrightnessCheck(volume: Int, brightness: Int) {
        if (volume < 0 || volume > 100) {
            throw IllegalArgumentException("volume")
        } else if (brightness < 0 || brightness > 100) {
            throw IllegalArgumentException("brightness")
        }
    }

    fun execute(targetActivity: List<Key>, volume: Int, brightness: Int): Pair<Int, Int> {
        var tempVolume = volume
        var tempBrightness = brightness
        try {
            volumeAndBrightnessCheck(volume, brightness)
        } catch (error: IllegalArgumentException) {
            if (error.message == "volume") {
                tempVolume = volume - min(abs(volume - 100), volume)
            } else if (error.message == "brightness") {
                tempBrightness = brightness - min(abs(brightness - 100), brightness)
            }
        }
        val tempLeft = mutableListOf<String>()
        val tempRight = mutableListOf<String>()

        targetActivity.forEach {
            if (it.command is StringCommand) {
                tempLeft.add(it.keyName)
            }
            var right = ""
            right += it.command
            if (it.command is StringCommand) {
                right += "\""
                right += it.keyName
                right += "\""
            }
            tempRight.add(right)
            if (it.command is UndoCommand && targetActivity.indexOf(it) - 1 >= 0) {
                when (targetActivity[targetActivity.indexOf(it) - 1].command) {
                    is StringCommand -> {
                        tempLeft.removeAt(tempLeft.size - 1)
                        tempLeft.removeAt(tempLeft.size - 1)
                    }

                    is VolumeUpCommand -> {
                        tempVolume -= 10
                    }

                    is VolumeDownCommand -> {
                        tempVolume += 10
                    }

                    is BrightnessUpCommand -> {
                        tempBrightness -= 10
                    }

                    is BrightnessDownCommand -> {
                        tempBrightness += 10
                    }
                }
            }
        }
        val left = tempLeft.joinToString().chunked(CONSOLE_SIZE / 2 - 1)
        val right = tempRight.joinToString().chunked(CONSOLE_SIZE / 2 - 1)

        var result = ""
        repeat(CONSOLE_SIZE) { result += "-" }
        result += "\n"
        for (index in 0..<max(left.size, right.size)) {
            if (index < left.size) result += left[index] else repeat(CONSOLE_SIZE / 2 - 1) { result += " " }
            if (index < left.size) repeat(CONSOLE_SIZE / 2 - 1 - left[index].length) { result += " " }
            result += "|"
            if (index < right.size) result += right[index] else repeat(CONSOLE_SIZE / 2 - 1) { result += " " }
            result += "\n"
        }
        var temp =
            "volumeLevel:" + "    " + Array(4 - tempVolume.toString().length) { " " }.joinToString("") + tempVolume.toString()
        result += temp
        repeat(CONSOLE_SIZE / 2 - 1 - temp.length) { result += " " }
        result += "|\n"
        temp =
            "brightnessLevel:" + Array(4 - tempBrightness.toString().length) { " " }.joinToString("") + tempBrightness.toString()
        result += temp
        repeat(CONSOLE_SIZE / 2 - 1 - temp.length) { result += " " }
        result += "|\n"
        repeat(CONSOLE_SIZE) { result += "-" }
        repeat(100) { println() } // чистим консоль от мусора
        println(result)
        return Pair(tempVolume, tempBrightness)
    }

}

class StringCommand : Command {

    override fun toString(): String {
        return "PressedKey: "
    }
}

class VolumeUpCommand : Command {
    override fun execute(targetActivity: List<Key>, volume: Int, brightness: Int): Pair<Int, Int> {
        super.execute(targetActivity, volume + 10, brightness)
        return Pair(volume + 10, brightness)
    }

    override fun toString(): String {
        return "VolumeUp"
    }
}

class VolumeDownCommand : Command {
    override fun execute(targetActivity: List<Key>, volume: Int, brightness: Int): Pair<Int, Int> {
        super.execute(targetActivity, volume - 10, brightness)
        return Pair(volume - 10, brightness)
    }

    override fun toString(): String {
        return "VolumeDown"
    }
}

class BrightnessUpCommand : Command {
    override fun execute(targetActivity: List<Key>, volume: Int, brightness: Int): Pair<Int, Int> {
        super.execute(targetActivity, volume, brightness + 10)
        return Pair(volume, brightness + 10)
    }

    override fun toString(): String {
        return "BrightnessUp"
    }
}

class BrightnessDownCommand : Command {
    override fun execute(targetActivity: List<Key>, volume: Int, brightness: Int): Pair<Int, Int> {
        super.execute(targetActivity, volume, brightness - 10)
        return Pair(volume, brightness - 10)
    }

    override fun toString(): String {
        return "BrightnessDown"
    }
}

class UndoCommand : Command {

    override fun toString(): String {
        return "Undo"
    }

}