package src

open class Command {
    open fun execute(targetActivity: List<Key>, volume: Int, brightness: Int) {
        TODO("Тут выполняется вывод в консоль")
    }
}

class CharCommand : Command() {
    override fun execute(targetActivity: List<Key>, volume: Int, brightness: Int) {
        TODO("Тут выполняютя действия с параметрами, а потом вызов родителя")
        super.execute(targetActivity, volume, brightness)
    }
}

class VolumeUpCommand : Command() {
    override fun execute(targetActivity: List<Key>, volume: Int, brightness: Int) {
        super.execute(targetActivity, volume, brightness)
    }
}

class VolumeDownCommand : Command() {
    override fun execute(targetActivity: List<Key>, volume: Int, brightness: Int) {
        super.execute(targetActivity, volume, brightness)
    }
}

class BrightnessUpCommand : Command() {
    override fun execute(targetActivity: List<Key>, volume: Int, brightness: Int) {
        super.execute(targetActivity, volume, brightness)
    }
}

class BrightnessDownCommand : Command() {
    override fun execute(targetActivity: List<Key>, volume: Int, brightness: Int) {
        super.execute(targetActivity, volume, brightness)
    }
}