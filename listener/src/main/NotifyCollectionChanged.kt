package src.main

/**Создать интерфейс INotifyCollectionChanged с методами AddInotifyPropertyChangedListener и RemoveNotifyPropertyChangedListener. В качестве аргументов методы должны принимать функции, которые выполняются в случае изменения объекта-коллекции, поддерживающего интерфейс. Создайте перечислитель со статусами: Added, Removed, ItemChanged и передавайте соответствующее значение перечислителя в качестве аргумента в подключаемые функции.*/

typealias forCollectionLambda = (status: CollectionElement, newElementValue: Int, index: Int) -> Unit

interface NotifyCollectionChanged {
    fun addNotifyPropertyChangedListener(func: forCollectionLambda) {

    }

    fun removeNotifyPropertyChangedListener(func: forCollectionLambda) {

    }
}