package src.src

import src.main.*

fun main() {
    val keyboard = Keyboard()
    val firstKey = Key("first", StringCommand())
    val secondKey = Key("second", StringCommand())
    val volumeKey = Key("",  VolumeUpCommand())
    val brightnessKey = Key("",  BrightnessUpCommand())
    keyboard.addKey(firstKey)
    keyboard.addKey(secondKey)
    keyboard.addKey(volumeKey)
    keyboard.addKey(brightnessKey)
    keyboard.pressKey(firstKey)
    Thread.sleep(2000)
    keyboard.pressKey(secondKey)
    Thread.sleep(2000)
    keyboard.pressKey(volumeKey)
    Thread.sleep(2000)
    keyboard.pressKey(brightnessKey)
    Thread.sleep(2000)
    keyboard.pressKey(volumeKey)
    Thread.sleep(2000)

    keyboard.undo()

}