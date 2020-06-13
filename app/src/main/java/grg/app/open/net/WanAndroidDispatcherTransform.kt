package grg.app.open.net

object WanAndroidDispatcherTransform {

    fun <D> transform(
        d: ApiWrapper<D>,
        errorCallBack: ((ApiWrapper<D>) -> Unit)? = null,
        onFinish: (() -> Unit)? = null
    ): D? {
        val d = when {
            d.errorCode == 0 -> {
                d.data
            }
            else -> {
                errorCallBack?.invoke(d)
                null
            }
        }
        onFinish?.invoke()
        return d
    }

}