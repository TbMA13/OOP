package src.main
class MySecondClass(property: Int) : NotifyPropertyChanging {
    private val listeners = mutableSetOf<forChangingLambda>()
    override fun addNotifyPropertyChangedListener(func: forChangingLambda) {
        listeners.add(func)
    }

    override fun removeNotifyPropertyChangedListener(func: forChangingLambda) {
        listeners.remove(func)
    }

    var property = property
        set(value) {
            val flag = listeners.any { it(field, value, true) }
            if (flag) {
                listeners.forEach { it(field, value, false) }
                field = value
            } else {
                listeners.forEach { it(field, field, false) }
            }
        }
}