package grg.app.open.net

import grg.app.open.ObserverObservable
import grg.app.open.net.bean.Data

class OOTransform<Data>(
    val target: ObserverObservable<Data?>,
    val errorDispatcher: (ApiWrapper<Data>.() -> Unit)? = null
) : ObserverObservable<ApiWrapper<Data>>() {

    override fun onChanged(t: ApiWrapper<Data>) {
        if (t.errorCode == 0) {
            target.onChanged(t.data)
        } else {
            errorDispatcher?.invoke(t)
            target.onChanged(null)
        }
    }

}