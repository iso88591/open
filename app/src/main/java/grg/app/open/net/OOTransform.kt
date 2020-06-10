package grg.app.open.net

import grg.app.open.ObserverObservable

class OOTransform<Data>(
    val target: ObserverObservable<Data?>,
    val onFinished: (() -> Unit)? = null,
    val errorDispatcher: (ApiWrapper<Data>.() -> Unit)? = null
) : ObserverObservable<ApiWrapper<Data>>() {

    override fun onChanged(t: ApiWrapper<Data>) {
        if (t.errorCode == 0) {
            target.onChanged(t.data)
        } else {
            errorDispatcher?.invoke(t)
            target.onChanged(null)
        }
        onFinished?.invoke()
    }

}