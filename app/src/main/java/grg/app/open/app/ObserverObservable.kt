package grg.app.open.app

import androidx.databinding.ObservableField
import androidx.lifecycle.Observer

open class ObserverObservable<T> : ObservableField<T>(), Observer<T> {

    override fun onChanged(t: T) {
        set(t)
    }



}