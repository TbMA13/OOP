package src.src

import src.main.Key
import src.main.Keyboard
import src.main.StringCommand
import src.main.VolumeUpCommand

fun main() {
    val keyboard = Keyboard()
    val firstKey = Key("first", StringCommand())
    val secondKey = Key("second", StringCommand())
    val thirdKey = Key("",  VolumeUpCommand())
    keyboard.addKey(firstKey)
    keyboard.addKey(secondKey)
    keyboard.addKey(thirdKey)
    keyboard.pressKey(firstKey)
    Thread.sleep(2000)
    keyboard.pressKey(secondKey)
    Thread.sleep(2000)
    keyboard.pressKey(thirdKey)
    Thread.sleep(2000)
    keyboard.undo()

}