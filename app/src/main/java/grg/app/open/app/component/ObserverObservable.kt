package grg.app.open.app.component

import androidx.databinding.ObservableField
import androidx.lifecycle.Observer

open class ObserverObservable<T>(
    private val onChangeCallBack: ((T?) -> Unit)? = null
) : ObservableField<T>(), Observer<T> {

    override fun onChanged(t: T) {
        set(t)
        onChangeCallBack?.invoke(t)
    }


}