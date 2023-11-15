package src.main

class MyFirstClass(property: Int) : NotifyPropertyChanged {
    private val listeners = mutableSetOf<forChanged>()
    override fun addNotifyPropertyChangedListener(func: forChanged) {
        listeners.add(func)
    }

    override fun removeNotifyPropertyChangedListener(func: forChanged) {
        listeners.remove(func)
    }

    var property = property
        set(value) {
            listeners.forEach { it(value) }
            field = value
        }
}