package src.main


class Keyboard {
    private val keys = mutableSetOf<Key>()
    private val lastActivity = mutableListOf<Key>()
    private var brightness = 0
    private var volume = 0

    fun addKey(newKey: Key) {
        keys.add(newKey.copy())
    }

    fun pressKey(targetKey: Key) {
        if (targetKey in keys || targetKey.command is UndoCommand) {
            lastActivity.add(targetKey.copy())
            when (targetKey.command) {
                is VolumeUpCommand, is VolumeDownCommand -> {
                    volume = targetKey.command.execute(lastActivity, volume, brightness).first
                }

                is BrightnessUpCommand, is BrightnessDownCommand -> {
                    brightness = targetKey.command.execute(lastActivity, volume, brightness).second
                }

                else -> {
                    targetKey.command.execute(lastActivity, volume, brightness)
                }
            }
        } else {
            throw NoSuchElementException("Среди $keys не найдено $targetKey")
        }
    }

    fun addActivity(keyName: String, command: Command) {
        val tempKey = Key(keyName, command)
        lastActivity.add(tempKey)
        this.pressKey(tempKey)
    }

    fun undo() {
        this.pressKey(Key("", UndoCommand()))
    }
}
