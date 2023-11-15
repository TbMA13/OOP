package src.main

fun main() {
    run {
        val firstTestObj = MyFirstClass(0)
        val listener = { new: Int -> println("Текущее значение параметра: $new") }
        firstTestObj.addNotifyPropertyChangedListener(listener)
        firstTestObj.property = 9
        firstTestObj.property = 20
    }

    println()

    run {
        val secondTestObj = MySecondClass(21)
        val listener: (Int, Int, Boolean) -> Boolean = { old: Int, new: Int, isCheck: Boolean ->
            var check = true
            if (isCheck) {
                check = new >= 0
            } else if(old != new) {
                println("Новое значение свойства $old: $new")
            } else {
                println("Значение свойства не изменилось: $new")
            }
            check
        }
        secondTestObj.addNotifyPropertyChangedListener(listener)
        secondTestObj.property = -10
        secondTestObj.property = 20
    }

    println()

    run {
        val testCollection = MyCollection()
        val listener = { status: CollectionElement, value: Int, index: Int ->
            when (status) {
                CollectionElement.Added -> {
                    println("Был добавлен элемент $value по индексу $index")
                }

                CollectionElement.Changed -> {
                    println("Был изменен элемент $value по индексу $index")
                }

                CollectionElement.Removed -> {
                    println("Был удален элемент $value по индексу $index")
                }
            }
        }
        testCollection.addNotifyPropertyChangedListener(listener)
        testCollection.addItem(0)
        testCollection.changeItem(0, 20)
        testCollection.removeItem(0)
    }
}