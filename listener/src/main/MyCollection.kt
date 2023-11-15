package src.main

class MyCollection : NotifyCollectionChanged {

    private val array = mutableListOf<Int>()
    private var listeners = mutableSetOf<forCollectionLambda>()
    override fun addNotifyPropertyChangedListener(func: forCollectionLambda) {
        listeners.add(func)
    }

    override fun removeNotifyPropertyChangedListener(func: forCollectionLambda) {
        listeners.remove(func)
    }

    fun addItem(addedItem: Int) {
        listeners.forEach { it(CollectionElement.Added, addedItem, array.size) }
        array.add(addedItem)
    }

    fun changeItem(index: Int, value: Int) {
        if (index >= array.size || index < 0) {
            throw IndexOutOfBoundsException("Размер массива: ${array.size}, затребованный индекс: $index")
        }
        listeners.forEach { it(CollectionElement.Changed, array[index], index) }
        array[index] = value
    }

    fun removeItem(index: Int) {
        if (index >= array.size || index < 0) {
            throw IndexOutOfBoundsException("Размер массива: ${array.size}, затребованный индекс: $index")
        }
        listeners.forEach { it(CollectionElement.Removed, array[index], index) }
        array.removeAt(index)
    }
}