package grg.app.open.net

import androidx.lifecycle.Observer
import grg.app.open.app.component.ObserverObservable

class OOTransform<Data>(
    var target: Observer<Data>? = null,
    val onFinished: (() -> Unit)? = null,
    val errorDispatcher: (ApiWrapper<Data>.() -> Unit)? = null
) : ObserverObservable<ApiWrapper<Data>>() {

    override fun onChanged(t: ApiWrapper<Data>) {
        val transform = WanAndroidDispatcherTransform.transform<Data>(t, errorDispatcher,onFinished)

        target?.onChanged(transform)

    }

}