package src.main


data class Key(var keyName: String = "", var command: Command) {
    override fun equals(other: Any?): Boolean {
        if (other is Key) {
            if (other.command is StringCommand && this.command is StringCommand ||
                other.command is VolumeUpCommand && this.command is VolumeUpCommand ||
                other.command is VolumeDownCommand && this.command is VolumeDownCommand ||
                other.command is BrightnessUpCommand && this.command is BrightnessUpCommand ||
                other.command is BrightnessDownCommand && this.command is BrightnessDownCommand ||
                other.command is UndoCommand && this.command is UndoCommand
            ) {
                return true
            }
        }
        return false
    }

    override fun hashCode(): Int {
        var result = keyName.hashCode()
        result = 31 * result + command.hashCode()
        return result
    }
}