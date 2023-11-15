package src.main


/** Создать интерфейс INotifyPropertyChanging
 *  с методами AddInotifyPropertyChangedListener и RemoveNotifyPropertyChangedListener.
 *  В качестве аргументов методы должны принимать функции,
 *  которые выполняются непосредственно перед изменением свойств объекта,
 *  поддерживающего интерфейс, с передачей информации о том с какого на какое значение
 *  свойство меняется и с возможностью отмены данного изменения*/

typealias forChangingLambda = (oldValue: Int, newValue: Int, isCheck: Boolean) -> Boolean

interface NotifyPropertyChanging {
    fun addNotifyPropertyChangedListener(func: forChangingLambda) {

    }

    fun removeNotifyPropertyChangedListener(func: forChangingLambda) {

    }
}