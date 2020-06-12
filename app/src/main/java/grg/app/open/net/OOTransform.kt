package grg.app.open.net

import androidx.lifecycle.Observer
import grg.app.open.app.ObserverObservable

class OOTransform<Data>(
    var target: Observer<Data>? = null,
    val onFinished: (() -> Unit)? = null,
    val errorDispatcher: (ApiWrapper<Data>.() -> Unit)? = null
) : ObserverObservable<ApiWrapper<Data>>() {

    override fun onChanged(t: ApiWrapper<Data>) {
        if (t.errorCode == 0) {
            target?.onChanged(t.data)
        } else {
            errorDispatcher?.invoke(t)
            target?.onChanged(null)
        }
        onFinished?.invoke()
    }

}