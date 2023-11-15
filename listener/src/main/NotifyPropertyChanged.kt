package src.main


/**
 *  Создать интерфейс INotifyPropertyChanged
 * с методами AddInotifyPropertyChangedListener и RemoveNotifyPropertyChangedListener.
 * В качестве аргументов методы должны принимать функции,
 * которые выполняются в случае изменения свойств объекта, поддерживающего интерфейс
 */

typealias forChanged = (Int) -> Unit
interface NotifyPropertyChanged {
    fun addNotifyPropertyChangedListener(func: forChanged) {

    }

    fun removeNotifyPropertyChangedListener(func: forChanged) {

    }
}